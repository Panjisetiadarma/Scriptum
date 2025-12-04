package scriptum.controllers;

import scriptum.database.DatabaseConnection;
import scriptum.models.Peminjaman;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PeminjamanController {
    
    // Buat peminjaman baru
    public static boolean createPeminjaman(Peminjaman peminjaman) {
        String query = "INSERT INTO peminjaman (user_id, lemari_id, tanggal_pinjam, tanggal_kembali, status) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, peminjaman.getUserId());
            pstmt.setInt(2, peminjaman.getLemariId());
            pstmt.setDate(3, Date.valueOf(peminjaman.getTanggalPinjam()));
            pstmt.setDate(4, Date.valueOf(peminjaman.getTanggalKembali()));
            pstmt.setString(5, peminjaman.getStatus());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error create peminjaman: " + e.getMessage());
            return false;
        }
    }
    
    // Ambil semua peminjaman
    public static List<Peminjaman> getAllPeminjaman() {
        List<Peminjaman> list = new ArrayList<>();
        String query = "SELECT * FROM peminjaman ORDER BY tanggal_pinjam DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Peminjaman p = new Peminjaman(
                    rs.getInt("user_id"),
                    rs.getInt("lemari_id"),
                    rs.getDate("tanggal_pinjam").toLocalDate(),
                    rs.getDate("tanggal_kembali").toLocalDate()
                );
                p.setId(rs.getInt("id"));
                p.setStatus(rs.getString("status"));
                if (rs.getDate("tanggal_dikembalikan") != null) {
                    p.setTanggalDikembalikan(rs.getDate("tanggal_dikembalikan").toLocalDate());
                }
                list.add(p);
            }
            
        } catch (SQLException e) {
            System.err.println("Error get all peminjaman: " + e.getMessage());
        }
        return list;
    }
    
    // Ambil peminjaman berdasarkan user
    public static List<Peminjaman> getPeminjamanByUser(int userId) {
        List<Peminjaman> list = new ArrayList<>();
        String query = "SELECT * FROM peminjaman WHERE user_id = ? ORDER BY tanggal_pinjam DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Peminjaman p = new Peminjaman(
                    rs.getInt("user_id"),
                    rs.getInt("lemari_id"),
                    rs.getDate("tanggal_pinjam").toLocalDate(),
                    rs.getDate("tanggal_kembali").toLocalDate()
                );
                p.setId(rs.getInt("id"));
                p.setStatus(rs.getString("status"));
                if (rs.getDate("tanggal_dikembalikan") != null) {
                    p.setTanggalDikembalikan(rs.getDate("tanggal_dikembalikan").toLocalDate());
                }
                list.add(p);
            }
            
        } catch (SQLException e) {
            System.err.println("Error get peminjaman by user: " + e.getMessage());
        }
        return list;
    }
    
    // Update status peminjaman
    public static boolean updateStatus(int peminjamanId, String status) {
        String query = "UPDATE peminjaman SET status = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, status);
            pstmt.setInt(2, peminjamanId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error update status peminjaman: " + e.getMessage());
            return false;
        }
    }
    
    // Kembalikan peminjaman
    public static boolean kembalikanPeminjaman(int peminjamanId) {
        String query = "UPDATE peminjaman SET status = 'selesai', tanggal_dikembalikan = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setDate(1, Date.valueOf(LocalDate.now()));
            pstmt.setInt(2, peminjamanId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error kembalikan peminjaman: " + e.getMessage());
            return false;
        }
    }
    
    // Cek apakah lemari sedang dipinjam
    public static boolean isLemariDipinjam(int lemariId) {
        String query = "SELECT COUNT(*) FROM peminjaman WHERE lemari_id = ? AND status = 'aktif'";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, lemariId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error cek lemari dipinjam: " + e.getMessage());
        }
        return false;
    }
}