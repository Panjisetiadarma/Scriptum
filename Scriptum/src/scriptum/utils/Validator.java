package scriptum.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Validator {
    
    // Validasi email
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) return true; // Email optional
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return Pattern.matches(regex, email);
    }
    
    // Validasi NIM (minimal 5 angka)
    public static boolean isValidNIM(String nim) {
        if (nim == null || nim.trim().isEmpty()) return false;
        return nim.matches("\\d{5,}");
    }
    
    // Validasi tidak kosong
    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
    
    // Validasi angka positif
    public static boolean isPositiveNumber(int number) {
        return number > 0;
    }
    
    // Validasi format tanggal
    public static boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    // Validasi tanggal tidak di masa lalu (untuk peminjaman)
    public static boolean isValidTanggalPinjam(LocalDate tanggal) {
        return !tanggal.isBefore(LocalDate.now());
    }
    
    // Validasi tanggal kembali harus setelah tanggal pinjam
    public static boolean isValidTanggalKembali(LocalDate pinjam, LocalDate kembali) {
        return !kembali.isBefore(pinjam);
    }
    
    // Validasi password minimal 6 karakter
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
    
    // Validasi kode lemari format: LM-XXX
    public static boolean isValidKodeLemari(String kode) {
        return kode != null && kode.matches("LM-\\d{3}");
    }
}