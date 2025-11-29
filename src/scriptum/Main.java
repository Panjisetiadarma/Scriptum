package scriptum;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Sistem Perpustakaan Scriptum ===");
        System.out.println("Aplikasi dimulai...");
        
        // Contoh penggunaan class
        User user = new User("admin", "password123", "admin@scriptum.com", "Admin Scriptum", "ADMIN");
        System.out.println("User dibuat: " + user.getNama());
        
        Buku buku = new Buku("Pemrograman Java", "John Doe", "Penerbit ABC", 2024, "Teknologi", 10);
        System.out.println("Buku ditambahkan: " + buku.getJudul());
    }
}

