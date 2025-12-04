package scriptum.models;

import java.time.LocalDateTime;

public class Notifikasi {
    private int id;
    private int userId;
    private String judul;
    private String pesan;
    private String jenis; // info, success, warning, error
    private boolean dibaca;
    private LocalDateTime createdAt;
    
    public Notifikasi(int userId, String judul, String pesan, String jenis) {
        this.userId = userId;
        this.judul = judul;
        this.pesan = pesan;
        this.jenis = jenis;
        this.dibaca = false;
        this.createdAt = LocalDateTime.now();
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
    
    public String getJudul() {
        return judul;
    }
    
    public void setJudul(String judul) {
        this.judul = judul;
    }
    
    public String getPesan() {
        return pesan;
    }
    
    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
    
    public String getJenis() {
        return jenis;
    }
    
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    
    public boolean isDibaca() {
        return dibaca;
    }
    
    public void setDibaca(boolean dibaca) {
        this.dibaca = dibaca;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}