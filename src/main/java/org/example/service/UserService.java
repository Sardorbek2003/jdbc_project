package org.example.service;

import org.example.dao.UserDao;
import org.example.model.User;

public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User registerUser(String username, String phoneNumber, String password, String email) throws Exception {
        // Validation
        if (password.length() < 8) {
            throw new Exception("Password must be at least 8 characters long.");
        }
        if (!password.matches(".*[A-Za-z].*") || !password.matches(".*[0-9].*") || !password.matches(".*[\\W_].*")) {
            throw new Exception("Password must contain letters, numbers, and special characters.");
        }
        if (!email.endsWith("@gmail.com") && !email.endsWith("@mail.ru")) {
            throw new Exception("Email must be a valid Gmail or Mail.ru address.");
        }

        User user = new User(0, username, phoneNumber, password, email); // Assuming id is not set at registration
        userDao.signUp(user);
        return user;
    }

    public boolean signIn(String username, String password) throws Exception {
        return userDao.signIn(username, password);
    }
}
