package scriptum;

import java.time.LocalDate;

public class Laporan {
    private int laporanId;
    private int adminId;
    private LocalDate tanggalLaporan;
    private int jumlahBuku;
    private int jumlahPinjaman;
    private int jumahUserAktif; // jumlah user aktif (ada typo di ERD: jumah_user_aktif)
    
    // Constructors
    public Laporan() {}
    
    public Laporan(int adminId, LocalDate tanggalLaporan, int jumlahBuku, int jumlahPinjaman, int jumahUserAktif) {
        this.adminId = adminId;
        this.tanggalLaporan = tanggalLaporan;
        this.jumlahBuku = jumlahBuku;
        this.jumlahPinjaman = jumlahPinjaman;
        this.jumahUserAktif = jumahUserAktif;
    }
    
    // Getters and Setters
    public int getLaporanId() { return laporanId; }
    public void setLaporanId(int laporanId) { this.laporanId = laporanId; }
    
    public int getAdminId() { return adminId; }
    public void setAdminId(int adminId) { this.adminId = adminId; }
    
    public LocalDate getTanggalLaporan() { return tanggalLaporan; }
    public void setTanggalLaporan(LocalDate tanggalLaporan) { this.tanggalLaporan = tanggalLaporan; }
    
    public int getJumlahBuku() { return jumlahBuku; }
    public void setJumlahBuku(int jumlahBuku) { this.jumlahBuku = jumlahBuku; }
    
    public int getJumlahPinjaman() { return jumlahPinjaman; }
    public void setJumlahPinjaman(int jumlahPinjaman) { this.jumlahPinjaman = jumlahPinjaman; }
    
    public int getJumahUserAktif() { return jumahUserAktif; }
    public void setJumahUserAktif(int jumahUserAktif) { this.jumahUserAktif = jumahUserAktif; }
}

