package scriptum.controllers;

import scriptum.database.DatabaseConnection;
import scriptum.models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    
    // Login user
    public static User login(String nim, String password) {
        User user = null;
        String query = "SELECT * FROM users WHERE nim = ? AND password = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, nim);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                user = new User(
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getString("email"),
                    rs.getString("prodi"),
                    rs.getInt("angkatan")
                );
                user.setId(rs.getInt("id"));
                user.setPassword(rs.getString("password"));
            }
            
        } catch (SQLException e) {
            System.err.println("Login error: " + e.getMessage());
        }
        
        return user;
    }
    
    // Ambil semua user
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users ORDER BY nama";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                User user = new User(
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getString("email"),
                    rs.getString("prodi"),
                    rs.getInt("angkatan")
                );
                user.setId(rs.getInt("id"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            
        } catch (SQLException e) {
            System.err.println("Error get users: " + e.getMessage());
        }
        
        return users;
    }
    
    // Tambah user baru
    public static boolean tambahUser(User user) {
        String query = "INSERT INTO users (nim, nama, email, prodi, angkatan, password) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, user.getNim());
            pstmt.setString(2, user.getNama());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getProdi());
            pstmt.setInt(5, user.getAngkatan());
            pstmt.setString(6, user.getPassword());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error tambah user: " + e.getMessage());
            return false;
        }
    }
    
    // Update user
    public static boolean updateUser(User user) {
        String query = "UPDATE users SET nim = ?, nama = ?, email = ?, prodi = ?, angkatan = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, user.getNim());
            pstmt.setString(2, user.getNama());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getProdi());
            pstmt.setInt(5, user.getAngkatan());
            pstmt.setInt(6, user.getId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error update user: " + e.getMessage());
            return false;
        }
    }
    
    // Hapus user
    public static boolean hapusUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, id);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error hapus user: " + e.getMessage());
            return false;
        }
    }
}