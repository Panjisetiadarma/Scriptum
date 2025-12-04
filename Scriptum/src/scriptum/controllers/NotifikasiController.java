package scriptum.controllers;

import scriptum.database.DatabaseConnection;
import scriptum.models.Notifikasi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotifikasiController {
    
    // Buat notifikasi baru
    public static boolean createNotifikasi(int userId, String judul, String pesan, String jenis) {
        String query = "INSERT INTO notifikasi (user_id, judul, pesan, jenis) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            pstmt.setString(2, judul);
            pstmt.setString(3, pesan);
            pstmt.setString(4, jenis);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error create notifikasi: " + e.getMessage());
            return false;
        }
    }
    
    // Ambil notifikasi oleh user
    public static List<Notifikasi> getNotifikasiByUser(int userId) {
        List<Notifikasi> list = new ArrayList<>();
        String query = "SELECT * FROM notifikasi WHERE user_id = ? ORDER BY created_at DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Notifikasi n = new Notifikasi(
                    rs.getInt("user_id"),
                    rs.getString("judul"),
                    rs.getString("pesan"),
                    rs.getString("jenis")
                );
                n.setId(rs.getInt("id"));
                n.setDibaca(rs.getBoolean("dibaca"));
                n.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                list.add(n);
            }
            
        } catch (SQLException e) {
            System.err.println("Error get notifikasi: " + e.getMessage());
        }
        return list;
    }
    
    // Tandai notifikasi sebagai dibaca
    public static boolean markAsRead(int notifikasiId) {
        String query = "UPDATE notifikasi SET dibaca = true WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, notifikasiId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error mark as read: " + e.getMessage());
            return false;
        }
    }
}