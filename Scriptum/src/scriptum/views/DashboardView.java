package scriptum.views;

import scriptum.models.Admin;
import scriptum.models.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardView extends JFrame {
    private Admin admin;
    private User user;
    
    public DashboardView(Admin admin, User user) {
        this.admin = admin;
        this.user = user;
        
        setTitle("Scriptum - Dashboard");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
    }
    
    private void initComponents() {
        // Panel utama dengan BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(0, 102, 204));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JLabel lblTitle = new JLabel("SCRIPTUM - DASHBOARD");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        
        String userInfo = (admin != null) ? 
            "Admin: " + admin.getNama() : 
            "User: " + user.getNama() + " (" + user.getNim() + ")";
        JLabel lblUser = new JLabel(userInfo);
        lblUser.setForeground(Color.WHITE);
        lblUser.setFont(new Font("Arial", Font.BOLD, 14));
        
        headerPanel.add(lblTitle, BorderLayout.WEST);
        headerPanel.add(lblUser, BorderLayout.EAST);
        
        // Menu Panel (Sidebar)
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        menuPanel.setPreferredSize(new Dimension(200, 0));
        menuPanel.setBackground(new Color(240, 240, 240));
        
        // Buat menu berdasarkan role
        String[] menuItems;
        if (admin != null) {
            // Menu untuk Admin
            menuItems = new String[]{
                "📊 Dashboard",
                "👥 User Management",
                "🗄️ Manajemen Lemari",
                "📝 Peminjaman",
                "📋 Laporan",
                "🔔 Notifikasi",
                "🚪 Logout"
            };
        } else {
            // Menu untuk User
            menuItems = new String[]{
                "📊 Dashboard",
                "📝 Ajukan Peminjaman",
                "📋 Riwayat Peminjaman",
                "🔔 Notifikasi",
                "🚪 Logout"
            };
        }
        
        for (String item : menuItems) {
            JButton btn = new JButton(item);
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            btn.setMaximumSize(new Dimension(180, 40));
            btn.setBackground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.addActionListener(new MenuActionListener(item));
            menuPanel.add(btn);
            menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        
        // Content Panel (Area Tengah)
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Welcome message
        String welcomeMsg = (admin != null) ? 
            "<html><h2>Selamat Datang, Admin!</h2>" +
            "<p>Sistem Manajemen Peminjaman Lemari</p>" +
            "<p>Fitur yang tersedia:</p>" +
            "<ul>" +
            "<li>Kelola data user</li>" +
            "<li>Kelola data lemari</li>" +
            "<li>Monitor peminjaman</li>" +
            "<li>Lihat laporan</li>" +
            "</ul></html>" :
            "<html><h2>Selamat Datang, " + user.getNama() + "!</h2>" +
            "<p>Sistem Peminjaman Lemari</p>" +
            "<p>Fitur yang tersedia:</p>" +
            "<ul>" +
            "<li>Ajukan peminjaman lemari</li>" +
            "<li>Lihat riwayat peminjaman</li>" +
            "<li>Lihat notifikasi</li>" +
            "</ul></html>";
        
        JLabel lblWelcome = new JLabel(welcomeMsg);
        lblWelcome.setVerticalAlignment(SwingConstants.TOP);
        
        // Statistik panel
        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Statistik"));
        
        JLabel lblStat1 = new JLabel("Total Peminjaman: 0", SwingConstants.CENTER);
        JLabel lblStat2 = new JLabel("Lemari Tersedia: 0", SwingConstants.CENTER);
        JLabel lblStat3 = new JLabel("Notifikasi: 0", SwingConstants.CENTER);
        JLabel lblStat4 = new JLabel("User Aktif: 0", SwingConstants.CENTER);
        
        lblStat1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        lblStat2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        lblStat3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        lblStat4.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        statsPanel.add(lblStat1);
        statsPanel.add(lblStat2);
        statsPanel.add(lblStat3);
        statsPanel.add(lblStat4);
        
        contentPanel.add(lblWelcome, BorderLayout.NORTH);
        contentPanel.add(statsPanel, BorderLayout.CENTER);
        
        // Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBorder(BorderFactory.createEtchedBorder());
        JLabel lblFooter = new JLabel("© 2024 Scriptum - Sistem Peminjaman Lemari");
        footerPanel.add(lblFooter);
        
        // Tambahkan semua panel ke main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(menuPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private class MenuActionListener implements ActionListener {
        private String menuItem;
        
        public MenuActionListener(String menuItem) {
            this.menuItem = menuItem;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (menuItem) {
                case "👥 User Management":
                    if (admin != null) {
                        JOptionPane.showMessageDialog(DashboardView.this, 
                            "Fitur User Management akan segera tersedia.");
                    }
                    break;
                case "🗄️ Manajemen Lemari":
                    if (admin != null) {
                        JOptionPane.showMessageDialog(DashboardView.this, 
                            "Fitur Manajemen Lemari akan segera tersedia.");
                    }
                    break;
                case "📝 Peminjaman":
                case "📝 Ajukan Peminjaman":
                    new PeminjamanView(user).setVisible(true);
                    break;
                case "📋 Riwayat Peminjaman":
                    new LaporanView(user).setVisible(true);
                    break;
                case "📋 Laporan":
                    if (admin != null) {
                        new LaporanView(null).setVisible(true);
                    }
                    break;
                case "🔔 Notifikasi":
                    new NotifikasiView(user).setVisible(true);
                    break;
                case "🚪 Logout":
                    int confirm = JOptionPane.showConfirmDialog(DashboardView.this, 
                        "Apakah Anda yakin ingin logout?", 
                        "Konfirmasi Logout", 
                        JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        new LoginView().setVisible(true);
                        dispose();
                    }
                    break;
            }
        }
    }
}