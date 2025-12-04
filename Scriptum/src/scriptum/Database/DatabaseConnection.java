package scriptum.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class DatabaseConnection {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/scriptum_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    // Singleton Pattern untuk koneksi database
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Koneksi database berhasil!");
            } catch (ClassNotFoundException e) {
                System.err.println("MySQL Driver tidak ditemukan!");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Koneksi database gagal! Error: " + e.getMessage());
                System.out.println("Pastikan MySQL berjalan dan database 'scriptum_db' sudah dibuat.");
            }
        }
        return connection;
    }
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Koneksi database ditutup.");
            } catch (SQLException e) {
                System.err.println("Gagal menutup koneksi: " + e.getMessage());
            }
        }
    }
    
    public static boolean testConnection() {
        try {
            getConnection();
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}