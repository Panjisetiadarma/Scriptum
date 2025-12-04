package scriptum.views;

import scriptum.controllers.AdminController;
import scriptum.controllers.UserController;
import scriptum.models.Admin;
import scriptum.models.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> cmbRole;
    private JButton btnLogin;
    
    public LoginView() {
        setTitle("Scriptum - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        initComponents();
    }
    
    private void initComponents() {
        // Panel utama
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel header
        JPanel headerPanel = new JPanel();
        JLabel lblTitle = new JLabel("SCRIPTUM");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0, 102, 204));
        headerPanel.add(lblTitle);
        
        // Panel form
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        
        JLabel lblRole = new JLabel("Login Sebagai:");
        cmbRole = new JComboBox<>(new String[]{"Admin", "User"});
        
        JLabel lblUsername = new JLabel("Username/NIM:");
        txtUsername = new JTextField();
        
        JLabel lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField();
        
        btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(0, 102, 204));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Tambah action listener untuk tombol login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        
        // Tambah tombol enter untuk login
        txtPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        
        // Tambah komponen ke form panel
        formPanel.add(lblRole);
        formPanel.add(cmbRole);
        formPanel.add(lblUsername);
        formPanel.add(txtUsername);
        formPanel.add(lblPassword);
        formPanel.add(txtPassword);
        formPanel.add(new JLabel()); // spacer
        formPanel.add(btnLogin);
        
        // Panel footer
        JPanel footerPanel = new JPanel();
        JLabel lblFooter = new JLabel("Sistem Manajemen Peminjaman Lemari");
        lblFooter.setForeground(Color.GRAY);
        footerPanel.add(lblFooter);
        
        // Tambah semua panel ke main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void login() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        String role = (String) cmbRole.getSelectedItem();
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Username dan password harus diisi!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if ("Admin".equals(role)) {
            Admin admin = AdminController.login(username, password);
            if (admin != null) {
                JOptionPane.showMessageDialog(this, 
                    "Login admin berhasil!\nSelamat datang " + admin.getNama(), 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Buka dashboard admin
                new DashboardView(admin, null).setVisible(true);
                dispose(); // Tutup window login
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Username atau password salah!", 
                    "Login Gagal", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } else {
            User user = UserController.login(username, password);
            if (user != null) {
                JOptionPane.showMessageDialog(this, 
                    "Login user berhasil!\nSelamat datang " + user.getNama(), 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Buka dashboard user
                new DashboardView(null, user).setVisible(true);
                dispose(); // Tutup window login
            } else {
                JOptionPane.showMessageDialog(this, 
                    "NIM atau password salah!", 
                    "Login Gagal", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Main method untuk testing
    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }
}