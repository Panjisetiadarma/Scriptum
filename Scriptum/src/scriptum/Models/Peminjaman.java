package scriptum.models;

import java.time.LocalDate;

public class Peminjaman {
    private int id;
    private int userId;
    private int lemariId;
    private LocalDate tanggalPinjam;
    private LocalDate tanggalKembali;
    private LocalDate tanggalDikembalikan;
    private String status; // aktif, selesai, terlambat
    
    // Constructor untuk peminjaman baru
    public Peminjaman(int userId, int lemariId, LocalDate tanggalPinjam, LocalDate tanggalKembali) {
        this.userId = userId;
        this.lemariId = lemariId;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
        this.status = "aktif";
    }
    
    // Getter dan Setter
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getLemariId() {
        return lemariId;
    }
    
    public void setLemariId(int lemariId) {
        this.lemariId = lemariId;
    }
    
    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }
    
    public void setTanggalPinjam(LocalDate tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }
    
    public LocalDate getTanggalKembali() {
        return tanggalKembali;
    }
    
    public void setTanggalKembali(LocalDate tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }
    
    public LocalDate getTanggalDikembalikan() {
        return tanggalDikembalikan;
    }
    
    public void setTanggalDikembalikan(LocalDate tanggalDikembalikan) {
        this.tanggalDikembalikan = tanggalDikembalikan;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    // Method untuk cek keterlambatan
    public boolean isTerlambat() {
        if (tanggalDikembalikan != null) {
            return tanggalDikembalikan.isAfter(tanggalKembali);
        }
        return LocalDate.now().isAfter(tanggalKembali);
    }
}