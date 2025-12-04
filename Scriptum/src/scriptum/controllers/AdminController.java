package scriptum.controllers;

import scriptum.database.DatabaseConnection;
import scriptum.models.Admin;
import java.sql.*;

public class AdminController {
    
    // Login admin
    public static Admin login(String username, String password) {
        Admin admin = null;
        String query = "SELECT * FROM admins WHERE username = ? AND password = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                admin = new Admin(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("nama_lengkap"),
                    rs.getString("email")
                );
                admin.setId(rs.getInt("id"));
            }
            
        } catch (SQLException e) {
            System.err.println("Login error: " + e.getMessage());
        }
        
        return admin;
    }
    
    // Tambah admin baru
    public static boolean tambahAdmin(Admin admin) {
        String query = "INSERT INTO admins (username, password, nama_lengkap, email) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, admin.getUsername());
            pstmt.setString(2, admin.getPassword());
            pstmt.setString(3, admin.getNama());
            pstmt.setString(4, admin.getEmail());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error tambah admin: " + e.getMessage());
            return false;
        }
    }
}