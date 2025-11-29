package scriptum;

public class AuthService {
    private UserDAO userDAO;
    
    public AuthService() {
        this.userDAO = new UserDAO();
    }
    
    public User login(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            user.setStatusLgn(true);
            userDAO.updateUserStatus(user.getUserId(), true);
            return user;
        }
        return null;
    }
    
    public boolean register(User user) {
        return userDAO.addUser(user);
    }
    
    public boolean logout(int userId) {
        return userDAO.updateUserStatus(userId, false);
    }
}

