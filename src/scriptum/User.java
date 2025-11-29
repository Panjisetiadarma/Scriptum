package scriptum;

import java.time.LocalDate;

public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String nama;
    private LocalDate tglDftr; // tanggal daftar
    private boolean statusLgn; // status login
    private String role; // ADMIN or USER
    
    // Constructors
    public User() {}
    
    public User(String username, String password, String email, String nama, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nama = nama;
        this.role = role;
        this.tglDftr = LocalDate.now();
        this.statusLgn = false;
    }
    
    // Getters and Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    
    public LocalDate getTglDftr() { return tglDftr; }
    public void setTglDftr(LocalDate tglDftr) { this.tglDftr = tglDftr; }
    
    public boolean isStatusLgn() { return statusLgn; }
    public void setStatusLgn(boolean statusLgn) { this.statusLgn = statusLgn; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}

