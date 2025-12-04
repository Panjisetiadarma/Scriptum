package scriptum.models;

public class Lemari {
    // ENCAPSULATION: Atribut private
    private int id;
    private String kodeLemari;
    private String lokasi;
    private String status; // tersedia, dipinjam, maintenance
    private int kapasitas;
    private String deskripsi;
    
    // Constructor
    public Lemari(String kodeLemari, String lokasi, int kapasitas, String deskripsi) {
        this.kodeLemari = kodeLemari;
        this.lokasi = lokasi;
        this.status = "tersedia";
        this.kapasitas = kapasitas;
        this.deskripsi = deskripsi;
    }
    
    // Getter dan Setter
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getKodeLemari() {
        return kodeLemari;
    }
    
    public void setKodeLemari(String kodeLemari) {
        this.kodeLemari = kodeLemari;
    }
    
    public String getLokasi() {
        return lokasi;
    }
    
    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getKapasitas() {
        return kapasitas;
    }
    
    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }
    
    public String getDeskripsi() {
        return deskripsi;
    }
    
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    
    // Method untuk cek ketersediaan
    public boolean isTersedia() {
        return "tersedia".equals(status);
    }
}