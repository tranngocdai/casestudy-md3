package com.case3.model;

import java.util.List;

public class User {
    private int id;
    private String fullName;
    private String phone;
    private String userName;
    private String password;
    private String role;
    private boolean status;

    public User() {
    }

    public User(int id, String fullName, String phone, String userName, String password, String role, boolean status) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
