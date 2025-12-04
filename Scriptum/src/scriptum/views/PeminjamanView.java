package scriptum.views;

import scriptum.models.User;
import scriptum.models.Peminjaman;
import scriptum.controllers.PeminjamanController;
import scriptum.controllers.NotifikasiController;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class PeminjamanView extends JFrame {
    private User user;
    private JTextField txtLemariId;
    private JTextField txtTanggalPinjam;
    private JTextField txtTanggalKembali;
    private JTextArea txtKeterangan;
    private JButton btnSubmit;
    private JButton btnCancel;
    
    public PeminjamanView(User user) {
        this.user = user;
        
        setTitle("Form Peminjaman Lemari");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        initComponents();
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        
        // User info (readonly)
        formPanel.add(new JLabel("Nama User:"));
        JTextField txtUser = new JTextField(user.getNama() + " (" + user.getNim() + ")");
        txtUser.setEditable(false);
        formPanel.add(txtUser);
        
        // Lemari ID
        formPanel.add(new JLabel("ID Lemari:*"));
        txtLemariId = new JTextField();
        formPanel.add(txtLemariId);
        
        // Tanggal Pinjam
        formPanel.add(new JLabel("Tanggal Pinjam:*"));
        JPanel datePanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtTanggalPinjam = new JTextField(LocalDate.now().toString(), 15);
        JButton btnToday1 = new JButton("Hari Ini");
        btnToday1.addActionListener(e -> txtTanggalPinjam.setText(LocalDate.now().toString()));
        datePanel1.add(txtTanggalPinjam);
        datePanel1.add(btnToday1);
        formPanel.add(datePanel1);
        
        // Tanggal Kembali
        formPanel.add(new JLabel("Tanggal Kembali:*"));
        JPanel datePanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtTanggalKembali = new JTextField(LocalDate.now().plusDays(7).toString(), 15);
        JButton btnToday2 = new JButton("+7 Hari");
        btnToday2.addActionListener(e -> txtTanggalKembali.setText(LocalDate.now().plusDays(7).toString()));
        datePanel2.add(txtTanggalKembali);
        datePanel2.add(btnToday2);
        formPanel.add(datePanel2);
        
        // Keterangan
        formPanel.add(new JLabel("Keterangan:"));
        txtKeterangan = new JTextArea(3, 20);
        txtKeterangan.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(txtKeterangan);
        formPanel.add(scrollPane);
        
        // Info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setBorder(BorderFactory.createTitledBorder("Informasi"));
        JLabel lblInfo = new JLabel("<html><small>* Wajib diisi<br>Format tanggal: YYYY-MM-DD</small></html>");
        infoPanel.add(lblInfo);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSubmit = new JButton("Ajukan Peminjaman");
        btnCancel = new JButton("Batal");
        
        btnSubmit.setBackground(new Color(0, 153, 76));
        btnSubmit.setForeground(Color.WHITE);
        btnCancel.setBackground(new Color(204, 0, 0));
        btnCancel.setForeground(Color.WHITE);
        
        btnSubmit.addActionListener(e -> submitPeminjaman());
        btnCancel.addActionListener(e -> dispose());
        
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnSubmit);
        
        // Tambahkan semua panel
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(infoPanel, BorderLayout.SOUTH);
        
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void submitPeminjaman() {
        try {
            // Validasi input
            if (txtLemariId.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID Lemari harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int lemariId = Integer.parseInt(txtLemariId.getText().trim());
            LocalDate tglPinjam = LocalDate.parse(txtTanggalPinjam.getText().trim());
            LocalDate tglKembali = LocalDate.parse(txtTanggalKembali.getText().trim());
            
            // Validasi tanggal
            if (tglPinjam.isBefore(LocalDate.now())) {
                JOptionPane.showMessageDialog(this, "Tanggal pinjam tidak boleh di masa lalu!");
                return;
            }
            
            if (tglKembali.isBefore(tglPinjam) || tglKembali.isEqual(tglPinjam)) {
                JOptionPane.showMessageDialog(this, "Tanggal kembali harus setelah tanggal pinjam!");
                return;
            }
            
            // Cek apakah lemari sedang dipinjam
            if (PeminjamanController.isLemariDipinjam(lemariId)) {
                JOptionPane.showMessageDialog(this, 
                    "Lemari dengan ID " + lemariId + " sedang dipinjam!", 
                    "Peringatan", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Buat objek peminjaman
            Peminjaman peminjaman = new Peminjaman(user.getId(), lemariId, tglPinjam, tglKembali);
            
            // Simpan ke database
            boolean success = PeminjamanController.createPeminjaman(peminjaman);
            
            if (success) {
                // Buat notifikasi
                NotifikasiController.createNotifikasi(
                    user.getId(),
                    "Peminjaman Berhasil",
                    "Peminjaman lemari berhasil diajukan. Tanggal kembali: " + tglKembali,
                    "success"
                );
                
                JOptionPane.showMessageDialog(this, 
                    "Peminjaman berhasil diajukan!\n" +
                    "ID Lemari: " + lemariId + "\n" +
                    "Tanggal Pinjam: " + tglPinjam + "\n" +
                    "Tanggal Kembali: " + tglKembali,
                    "Sukses", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Gagal mengajukan peminjaman. Silakan coba lagi.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID Lemari harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}