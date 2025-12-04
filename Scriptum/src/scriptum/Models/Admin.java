package scriptum.models;

// INHERITANCE: Admin mewarisi dari Person
public class Admin extends Person {
    private String username;
    private String password;
    
    public Admin(String username, String password, String nama, String email) {
        super(nama, email);
        this.username = username;
        this.password = password;
    }
    
    // POLYMORPHISM: Override method dari parent class
    @Override
    public void displayInfo() {
        System.out.println("Admin: " + getNama() + " (" + username + ")");
    }
    
    // ENCAPSULATION: Getter dan Setter
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    // Method khusus admin
    public boolean validatePassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}