package com.case3.service.user;

import com.case3.model.User;

import java.util.List;

public class UserService implements IUserService<User>{

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void edit(User user, int id) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return null;
    }
}
