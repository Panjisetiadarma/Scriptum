package scriptum;

import java.time.LocalDate;

public class Notifikasi {
    private int notifikasiId;
    private int userId;
    private String isiPesan;
    private LocalDate tanggalKirim;
    private boolean statusBaca;
    
    // Constructors
    public Notifikasi() {}
    
    public Notifikasi(int userId, String isiPesan, LocalDate tanggalKirim) {
        this.userId = userId;
        this.isiPesan = isiPesan;
        this.tanggalKirim = tanggalKirim;
        this.statusBaca = false;
    }
    
    // Getters and Setters
    public int getNotifikasiId() { return notifikasiId; }
    public void setNotifikasiId(int notifikasiId) { this.notifikasiId = notifikasiId; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public String getIsiPesan() { return isiPesan; }
    public void setIsiPesan(String isiPesan) { this.isiPesan = isiPesan; }
    
    public LocalDate getTanggalKirim() { return tanggalKirim; }
    public void setTanggalKirim(LocalDate tanggalKirim) { this.tanggalKirim = tanggalKirim; }
    
    public boolean isStatusBaca() { return statusBaca; }
    public void setStatusBaca(boolean statusBaca) { this.statusBaca = statusBaca; }
}

