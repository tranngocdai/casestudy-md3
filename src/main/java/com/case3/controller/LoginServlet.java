package com.case3.controller;

import com.case3.model.User;
import com.case3.service.user.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "login", value = "/homepage")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                break;
            default:
                showHomepage(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "login":
                login(request, response);
                break;
            case "singUp":
                singUp(request, response);
            default:
                showHomepage(request, response);
        }
    }

    private void singUp(HttpServletRequest request, HttpServletResponse response) {

    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService userService = new UserService();
        User user = userService.findByUsernameAndPassword(username, password);
        String destPage;
        if (user != null) {
            if (user.isStatus() && user.getRole().equals("other")) {
                HttpSession session = request.getSession();
                request.setAttribute("message", null);
                session.setAttribute("user", user);
                destPage = "/revenue";
            } else if (user.isStatus() && user.getRole().equals("admin")) {
                HttpSession session = request.getSession();
                request.setAttribute("message", null);
                session.setAttribute("user", user);
                destPage = "/admin.jsp";
            } else {
                destPage = "/homepage?action=&&username=&&password=";
                request.setAttribute("message", "Tài khoản của bạn bị khóa");
            }
        } else {
            destPage = "/homepage?action=&&username=&&password=";
            request.setAttribute("message", "Tài khoản không đúng, yêu cầu đăng nhập lại hoặc đăng xuất");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showHomepage(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
