package tubes;

package com.perpustakaan.service;

import com.perpustakaan.dao.UserDAO;
import com.perpustakaan.model.User;
import com.perpustakaan.util.PasswordUtil;

public class AuthService {
    private UserDAO userDAO;
    
    public AuthService() {
        this.userDAO = new UserDAO();
    }
    
    public User login(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        if (user != null && PasswordUtil.verifyPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }
    
    public boolean register(User user) {
        // Hash password before storing
        String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        
        return userDAO.addUser(user);
    }
    
    public boolean logout(HttpSession session) {
        session.invalidate();
        return true;
    }
}
