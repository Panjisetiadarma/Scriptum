package scriptum.models;

// INHERITANCE: User mewarisi dari Person
public class User extends Person {
    private String nim;
    private String prodi;
    private int angkatan;
    private String password;
    
    public User(String nim, String nama, String email, String prodi, int angkatan) {
        super(nama, email);
        this.nim = nim;
        this.prodi = prodi;
        this.angkatan = angkatan;
        this.password = "password123"; // default password
    }
    
    // POLYMORPHISM: Override method dari parent class
    @Override
    public void displayInfo() {
        System.out.println("User: " + getNama() + " (" + nim + ") - " + prodi);
    }
    
    // ENCAPSULATION: Getter dan Setter
    public String getNim() {
        return nim;
    }
    
    public void setNim(String nim) {
        this.nim = nim;
    }
    
    public String getProdi() {
        return prodi;
    }
    
    public void setProdi(String prodi) {
        this.prodi = prodi;
    }
    
    public int getAngkatan() {
        return angkatan;
    }
    
    public void setAngkatan(int angkatan) {
        this.angkatan = angkatan;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}