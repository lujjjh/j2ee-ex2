package org.ljh.service;

import org.ljh.dao.UserDao;
import org.ljh.model.User;

import java.sql.SQLException;

public class UserService {
    private UserDao userDao = null;

    public UserService() {
        try {
            userDao = new UserDao();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean create(String username, String password) {
        if (userDao == null) {
            return false;
        }
        try {
            return userDao.create(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User find(String username) {
        if (userDao == null) {
            return null;
        }
        User user = null;
        try {
            user = userDao.getUserByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User find(String username, String password) {
        if (userDao == null) {
            return null;
        }
        User user = null;
        try {
            user = userDao.authenticate(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
