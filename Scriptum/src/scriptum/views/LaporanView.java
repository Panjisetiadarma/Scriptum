package scriptum.views;

import scriptum.controllers.PeminjamanController;
import scriptum.models.Peminjaman;
import scriptum.models.User;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LaporanView extends JFrame {
    private User user;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> cmbFilter;
    private JButton btnRefresh;
    
    public LaporanView(User user) {
        this.user = user;
        
        String title = (user == null) ? "Laporan Semua Peminjaman" : "Riwayat Peminjaman";
        setTitle("Scriptum - " + title);
        setSize(900, 500);
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
        JLabel lblTitle = new JLabel((user == null) ? "LAPORAN SEMUA PEMINJAMAN" : "RIWAYAT PEMINJAMAN");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        
        // Filter panel
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Filter:"));
        cmbFilter = new JComboBox<>(new String[]{"Semua", "Aktif", "Selesai", "Terlambat"});
        btnRefresh = new JButton("Refresh");
        
        btnRefresh.addActionListener(e -> loadData());
        cmbFilter.addActionListener(e -> filterData());
        
        filterPanel.add(cmbFilter);
        filterPanel.add(btnRefresh);
        
        headerPanel.add(lblTitle, BorderLayout.WEST);
        headerPanel.add(filterPanel, BorderLayout.EAST);
        
        // Tabel
        String[] columns = {"ID", "User ID", "Lemari ID", "Tanggal Pinjam", "Tanggal Kembali", "Status", "Dikembalikan"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(70);  // User ID
        table.getColumnModel().getColumn(2).setPreferredWidth(70);  // Lemari ID
        table.getColumnModel().getColumn(3).setPreferredWidth(100); // Tanggal Pinjam
        table.getColumnModel().getColumn(4).setPreferredWidth(100); // Tanggal Kembali
        table.getColumnModel().getColumn(5).setPreferredWidth(80);  // Status
        table.getColumnModel().getColumn(6).setPreferredWidth(100); // Dikembalikan
        
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Button panel untuk admin
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        if (user == null) { // Admin view
            JButton btnExport = new JButton("Export PDF");
            JButton btnPrint = new JButton("Print");
            JButton btnKembalikan = new JButton("Tandai Dikembalikan");
            
            btnExport.addActionListener(e -> exportPDF());
            btnPrint.addActionListener(e -> printTable());
            btnKembalikan.addActionListener(e -> markAsReturned());
            
            buttonPanel.add(btnExport);
            buttonPanel.add(btnPrint);
            buttonPanel.add(btnKembalikan);
        }
        
        // Status panel
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblStatus = new JLabel("Total data: 0");
        statusPanel.add(lblStatus);
        
        // Tambahkan semua panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(statusPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void loadData() {
        // Kosongkan tabel
        tableModel.setRowCount(0);
        
        // Ambil data dari controller
        List<Peminjaman> list;
        if (user == null) {
            list = PeminjamanController.getAllPeminjaman();
        } else {
            list = PeminjamanController.getPeminjamanByUser(user.getId());
        }
        
        // Isi tabel
        for (Peminjaman p : list) {
            Object[] row = {
                p.getId(),
                p.getUserId(),
                p.getLemariId(),
                p.getTanggalPinjam(),
                p.getTanggalKembali(),
                p.getStatus(),
                (p.getTanggalDikembalikan() != null) ? p.getTanggalDikembalikan() : "-"
            };
            tableModel.addRow(row);
        }
        
        // Update status
        updateStatusLabel(list.size());
    }
    
    private void filterData() {
        String filter = (String) cmbFilter.getSelectedItem();
        if ("Semua".equals(filter)) {
            loadData();
            return;
        }
        
        // Filter berdasarkan status
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String status = (String) tableModel.getValueAt(i, 5);
            boolean visible = status.equalsIgnoreCase(filter);
            table.setRowHeight(i, visible ? 25 : 0);
        }
    }
    
    private void updateStatusLabel(int count) {
        JPanel statusPanel = (JPanel) ((BorderLayout) getContentPane().getLayout()).getLayoutComponent(BorderLayout.SOUTH);
        if (statusPanel != null) {
            ((JLabel) statusPanel.getComponent(0)).setText("Total data: " + count);
        }
    }
    
    private void exportPDF() {
        JOptionPane.showMessageDialog(this, "Fitur export PDF akan segera tersedia.");
    }
    
    private void printTable() {
        try {
            table.print();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error printing: " + e.getMessage());
        }
    }
    
    private void markAsReturned() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data peminjaman terlebih dahulu!");
            return;
        }
        
        int peminjamanId = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Tandai peminjaman ID " + peminjamanId + " sebagai dikembalikan?", 
            "Konfirmasi", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = PeminjamanController.kembalikanPeminjaman(peminjamanId);
            if (success) {
                JOptionPane.showMessageDialog(this, "Peminjaman berhasil ditandai sebagai dikembalikan.");
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengupdate status peminjaman.");
            }
        }
    }
}