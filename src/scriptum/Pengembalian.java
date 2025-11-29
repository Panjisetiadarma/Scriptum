package scriptum;

import java.time.LocalDate;

public class Pengembalian {
    private int pengembalianId;
    private int peminjamanId;
    private LocalDate tanggalKembali;
    private double denda;
    
    // Constructors
    public Pengembalian() {}
    
    public Pengembalian(int peminjamanId, LocalDate tanggalKembali, double denda) {
        this.peminjamanId = peminjamanId;
        this.tanggalKembali = tanggalKembali;
        this.denda = denda;
    }
    
    // Getters and Setters
    public int getPengembalianId() { return pengembalianId; }
    public void setPengembalianId(int pengembalianId) { this.pengembalianId = pengembalianId; }
    
    public int getPeminjamanId() { return peminjamanId; }
    public void setPeminjamanId(int peminjamanId) { this.peminjamanId = peminjamanId; }
    
    public LocalDate getTanggalKembali() { return tanggalKembali; }
    public void setTanggalKembali(LocalDate tanggalKembali) { this.tanggalKembali = tanggalKembali; }
    
    public double getDenda() { return denda; }
    public void setDenda(double denda) { this.denda = denda; }
}

