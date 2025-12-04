package scriptum;

import scriptum.views.LoginView;
import scriptum.database.DatabaseConnection;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== APLIKASI SCRIPTUM ===");
        System.out.println("Sistem Manajemen Peminjaman Lemari");
        
        // Test koneksi database
        System.out.println("Testing koneksi database...");
        if (DatabaseConnection.testConnection()) {
            System.out.println("✓ Koneksi database berhasil!");
        } else {
            System.out.println("✗ Koneksi database gagal!");
            System.out.println("Pastikan MySQL berjalan dan database 'scriptum_db' sudah dibuat.");
            return;
        }
        
        // Jalankan GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Set look and feel
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    
                    // Tampilkan login form
                    LoginView loginView = new LoginView();
                    loginView.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                        "Error: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}