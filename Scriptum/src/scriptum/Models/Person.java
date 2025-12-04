package scriptum.models;

import java.time.LocalDateTime;

// ABSTRACTION: Abstract class sebagai blueprint
public abstract class Person {
    protected int id;
    protected String nama;
    protected String email;
    protected LocalDateTime createdAt;
    
    // Constructor
    public Person(String nama, String email) {
        this.nama = nama;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }
    
    // Abstract method (POLYMORPHISM)
    public abstract void displayInfo();
    
    // ENCAPSULATION: Getter dan Setter
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}