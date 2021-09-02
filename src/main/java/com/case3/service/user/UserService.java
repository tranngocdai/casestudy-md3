package com.case3.service.user;

import com.case3.config.ConnectionJDBC;
import com.case3.model.Category;
import com.case3.model.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService<E> implements IUserService<Users>{

    Connection connection = ConnectionJDBC.getConnection();
//    public static final String SELECT_USERS = "SELECT * FROM users WHERE username = ? and password =?;";
    public static final String SELECT_ALL_USERS = "SELECT * FROM users;";
//    public static final String SELECT_roleUser = "SELECT * FROM role JOIN users on role.id_role = user.id where username ; ";
//    public static final String INSERT_NEW_USERS = "insert into USERS (fullName, phone,username,password) VALUE (?, ?, ?, ?);";
    public static final String SELECT_USERS_BY_ID = "SELECT * FROM users where id_users = ?;";
    private static final String ADD_EX_CATE = "insert into expenditure_categories(name_ec,id_icon,id_user) value (?,?,?)";
    private static final String ADD_RE_CATE = "insert into revenue_categories(name_ec,id_icon,id_user) value (?,?,?)";
    private static final String FIND_USER = "select * from users join role on users.id_role = role.id_role where username = ? and password = ?;";
    private static final String ADD_USER = "insert into users(fullName, phone, username, password, id_role) VALUE (?,?,?,?,?);";
    private static List<Category> listReCate = new ArrayList<>();
    private static List<Category> listExCate = new ArrayList<>();

    @Override
    public List<Users> findAll() {
        List<Users> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id_user");
                String fullName = resultSet.getString("fullName");
                String phone = resultSet.getString("phone");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                boolean status = resultSet.getBoolean("status");
                Users user = new Users(id, fullName, phone, username, password, role,status);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Users findById(int id) {
        Users users = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_USERS_BY_ID);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Users user) {
        try (
                PreparedStatement preAddUser = connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement preAddExCate = connection.prepareStatement(ADD_EX_CATE);
                PreparedStatement preAddReCate = connection.prepareStatement(ADD_RE_CATE)
        ) {
            connection.setAutoCommit(false);
            preAddUser.setString(1, user.getFullName());
            preAddUser.setString(2, user.getPhone());
            preAddUser.setString(3, user.getUsername());
            preAddUser.setString(4, user.getPassword());
            preAddUser.setInt(5, 2);
            preAddUser.executeUpdate();
            ResultSet resultSet = preAddUser.getGeneratedKeys();
            int idUser = -1;
            if (resultSet.next()) {
                idUser = resultSet.getInt(1);
            }
            resultSet.close();

            int idIcon = 1;
            for (Category category : listExCate
            ) {
                preAddExCate.setString(1, category.getName());
                preAddExCate.setInt(2, idIcon);
                preAddExCate.setInt(3, idUser);
                preAddExCate.executeUpdate();
                idIcon++;
            }
            for (Category category : listReCate
            ) {
                preAddReCate.setString(1, category.getName());
                preAddReCate.setInt(2, idIcon);
                preAddReCate.setInt(3, idUser);
                preAddReCate.executeUpdate();
                idIcon++;
            }
            connection.commit();
        }catch (SQLException throwables) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                throwables.printStackTrace();
            }
        }


    @Override
    public void edit(Users user, int id) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Users findByUsernameAndPassword(String username, String password) {
            Users users = null;
            try (PreparedStatement preFindUser = connection.prepareStatement(FIND_USER)) {
                preFindUser.setString(1, username);
                preFindUser.setString(2, password);
                ResultSet resultSet = preFindUser.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id_user");
                    String fullName = resultSet.getString("fullname");
                    String phone = resultSet.getString("phone");
                    String role = resultSet.getString("name_role");
                    boolean status = resultSet.getBoolean("status");
                    users = new Users(fullName, phone, username, password, role, status);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return users;

    }



}
