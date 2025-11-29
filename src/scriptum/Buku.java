package scriptum;

public class Buku {
    private int bukuId;
    private String judul;
    private String penulis;
    private String penerbit;
    private int thnTerbit; // tahun terbit
    private String ktegori; // kategori
    private int stok;
    
    // Constructors
    public Buku() {}
    
    public Buku(String judul, String penulis, String penerbit, int thnTerbit, String ktegori, int stok) {
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.thnTerbit = thnTerbit;
        this.ktegori = ktegori;
        this.stok = stok;
    }
    
    // Getters and Setters
    public int getBukuId() { return bukuId; }
    public void setBukuId(int bukuId) { this.bukuId = bukuId; }
    
    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }
    
    public String getPenulis() { return penulis; }
    public void setPenulis(String penulis) { this.penulis = penulis; }
    
    public String getPenerbit() { return penerbit; }
    public void setPenerbit(String penerbit) { this.penerbit = penerbit; }
    
    public int getThnTerbit() { return thnTerbit; }
    public void setThnTerbit(int thnTerbit) { this.thnTerbit = thnTerbit; }
    
    public String getKtegori() { return ktegori; }
    public void setKtegori(String ktegori) { this.ktegori = ktegori; }
    
    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }
}

