package scriptum.views;

import scriptum.models.User;
import scriptum.controllers.NotifikasiController;
import scriptum.models.Notifikasi;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class NotifikasiView extends JFrame {
    private User user;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblUnreadCount;
    
    public NotifikasiView(User user) {
        this.user = user;
        
        setTitle("Notifikasi - " + user.getNama());
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        initComponents();
        loadData();
    }
    
    private void initComponents() {
        // Panel utama
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel lblTitle = new JLabel("NOTIFIKASI");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        
        lblUnreadCount = new JLabel("Belum dibaca: 0");
        lblUnreadCount.setForeground(Color.RED);
        lblUnreadCount.setFont(new Font("Arial", Font.BOLD, 12));
        
        headerPanel.add(lblTitle, BorderLayout.WEST);
        headerPanel.add(lblUnreadCount, BorderLayout.EAST);
        
        // Tabel
        String[] columns = {"", "Judul", "Pesan", "Tanggal", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return Icon.class;
                return String.class;
            }
        };
        
        table = new JTable(tableModel);
        table.setRowHeight(40);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);  // Icon
        table.getColumnModel().getColumn(1).setPreferredWidth(150); // Judul
        table.getColumnModel().getColumn(2).setPreferredWidth(250); // Pesan
        table.getColumnModel().getColumn(3).setPreferredWidth(120); // Tanggal
        table.getColumnModel().getColumn(4).setPreferredWidth(80);  // Status
        
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnRefresh = new JButton("Refresh");
        JButton btnMarkAsRead = new JButton("Tandai Dibaca");
        JButton btnDelete = new JButton("Hapus");
        
        btnRefresh.addActionListener(e -> loadData());
        btnMarkAsRead.addActionListener(e -> markAsRead());
        btnDelete.addActionListener(e -> deleteNotification());
        
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnMarkAsRead);
        buttonPanel.add(btnDelete);
        
        // Tambahkan semua panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void loadData() {
        // Kosongkan tabel
        tableModel.setRowCount(0);
        
        // Ambil data dari controller
        List<Notifikasi> list = NotifikasiController.getNotifikasiByUser(user.getId());
        
        // Icon berdasarkan jenis notifikasi
        Icon infoIcon = UIManager.getIcon("OptionPane.informationIcon");
        Icon warningIcon = UIManager.getIcon("OptionPane.warningIcon");
        Icon errorIcon = UIManager.getIcon("OptionPane.errorIcon");
        
        // Isi tabel
        for (Notifikasi n : list) {
            Icon icon;
            switch (n.getJenis()) {
                case "success": icon = infoIcon; break;
                case "warning": icon = warningIcon; break;
                case "error": icon = errorIcon; break;
                default: icon = infoIcon;
            }
            
            Object[] row = {
                icon,
                n.getJudul(),
                n.getPesan(),
                n.getCreatedAt().toString(),
                n.isDibaca() ? "Dibaca" : "Belum"
            };
            tableModel.addRow(row);
        }
        
        // Update count
        int unreadCount = NotifikasiController.countUnread(user.getId());
        lblUnreadCount.setText("Belum dibaca: " + unreadCount);
    }
    
    private void markAsRead() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih notifikasi terlebih dahulu!");
            return;
        }
        
        List<Notifikasi> list = NotifikasiController.getNotifikasiByUser(user.getId());
        if (selectedRow < list.size()) {
            Notifikasi notif = list.get(selectedRow);
            boolean success = NotifikasiController.markAsRead(notif.getId());
            if (success) {
                JOptionPane.showMessageDialog(this, "Notifikasi ditandai sebagai dibaca.");
                loadData();
            }
        }
    }
    
    private void deleteNotification() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih notifikasi terlebih dahulu!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Hapus notifikasi ini?", 
            "Konfirmasi", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            // Note: Anda perlu menambahkan method delete di controller
            JOptionPane.showMessageDialog(this, "Fitur hapus akan segera tersedia.");
        }
    }
}