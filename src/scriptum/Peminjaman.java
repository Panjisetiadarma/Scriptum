package scriptum;

import java.time.LocalDate;

public class Peminjaman {
    private int idPeminjam;
    private int iserId; // user_id (ada typo di ERD: iser_id)
    private int bukuId;
    private LocalDate tglPinjm; // tanggal pinjam
    private String statusPeminjaman;
    private LocalDate jatuhTempo;
    
    // Constructors
    public Peminjaman() {}
    
    public Peminjaman(int iserId, int bukuId, LocalDate tglPinjm, LocalDate jatuhTempo) {
        this.iserId = iserId;
        this.bukuId = bukuId;
        this.tglPinjm = tglPinjm;
        this.jatuhTempo = jatuhTempo;
        this.statusPeminjaman = "ACTIVE";
    }
    
    // Getters and Setters
    public int getIdPeminjam() { return idPeminjam; }
    public void setIdPeminjam(int idPeminjam) { this.idPeminjam = idPeminjam; }
    
    public int getIserId() { return iserId; }
    public void setIserId(int iserId) { this.iserId = iserId; }
    
    public int getBukuId() { return bukuId; }
    public void setBukuId(int bukuId) { this.bukuId = bukuId; }
    
    public LocalDate getTglPinjm() { return tglPinjm; }
    public void setTglPinjm(LocalDate tglPinjm) { this.tglPinjm = tglPinjm; }
    
    public String getStatusPeminjaman() { return statusPeminjaman; }
    public void setStatusPeminjaman(String statusPeminjaman) { this.statusPeminjaman = statusPeminjaman; }
    
    public LocalDate getJatuhTempo() { return jatuhTempo; }
    public void setJatuhTempo(LocalDate jatuhTempo) { this.jatuhTempo = jatuhTempo; }
}

