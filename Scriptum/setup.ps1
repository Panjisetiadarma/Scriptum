Write-Host "=== SETUP SCRIPTUM PROJECT ===" -ForegroundColor Green

# 1. Buat struktur folder
Write-Host "1. Membuat struktur folder..." -ForegroundColor Cyan
$folders = @(
    "src\scriptum\database",
    "src\scriptum\models", 
    "src\scriptum\controllers",
    "src\scriptum\views",
    "src\scriptum\utils",
    "database",
    "bin",
    "lib"
)

foreach ($folder in $folders) {
    New-Item -ItemType Directory -Path $folder -Force | Out-Null
    Write-Host "   ✅ $folder" -ForegroundColor Gray
}

# 2. Download MySQL Connector jika belum ada
Write-Host "2. Checking MySQL Connector..." -ForegroundColor Cyan
$jarPath = "lib\mysql-connector-java-8.0.28.jar"
if (-not (Test-Path $jarPath)) {
    Write-Host "   Downloading MySQL Connector..." -ForegroundColor Yellow
    $url = "https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.28/mysql-connector-java-8.0.28.jar"
    Invoke-WebRequest -Uri $url -OutFile $jarPath
    Write-Host "   ✅ MySQL Connector downloaded" -ForegroundColor Green
} else {
    Write-Host "   ✅ MySQL Connector sudah ada" -ForegroundColor Green
}

# 3. Buat database script
Write-Host "3. Membuat script database..." -ForegroundColor Cyan
@'
-- Script untuk membuat database Scriptum
CREATE DATABASE IF NOT EXISTS scriptum_db;
USE scriptum_db;

CREATE TABLE IF NOT EXISTS admins (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    nama_lengkap VARCHAR(100) NOT NULL,
    email VARCHAR(100)
);

INSERT INTO admins (username, password, nama_lengkap, email) 
VALUES ('admin', 'admin123', 'Administrator', 'admin@scriptum.com')
ON DUPLICATE KEY UPDATE id=id;

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nim VARCHAR(20) UNIQUE NOT NULL,
    nama VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    prodi VARCHAR(50),
    angkatan INT
);

INSERT INTO users (nim, nama, email, prodi, angkatan) 
VALUES 
('2023001', 'Budi Santoso', 'budi@email.com', 'Teknik Informatika', 2023),
('2023002', 'Sari Wijaya', 'sari@email.com', 'Sistem Informasi', 2023)
ON DUPLICATE KEY UPDATE id=id;
'@ | Out-File -FilePath "database\scriptum_db.sql" -Encoding UTF8

Write-Host "   ✅ Script database dibuat" -ForegroundColor Green

# 4. Buat file Java minimal
Write-Host "4. Membuat file Java minimal..." -ForegroundColor Cyan

# Main.java
@'
package scriptum;

import scriptum.database.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== APLIKASI SCRIPTUM ===");
        System.out.println("Sistem Manajemen Peminjaman Lemari");
        
        System.out.println("Testing koneksi database...");
        if (DatabaseConnection.testConnection()) {
            System.out.println("✅ Koneksi database berhasil!");
        } else {
            System.out.println("❌ Koneksi database gagal!");
        }
    }
}
'@ | Out-File -FilePath "src\scriptum\Main.java" -Encoding UTF8

# DatabaseConnection.java
@'
package scriptum.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/scriptum_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Connected to database");
            }
        } catch (Exception e) {
            System.err.println("❌ Database connection failed: " + e.getMessage());
        }
        return connection;
    }
    
    public static boolean testConnection() {
        try {
            getConnection();
            return connection != null && !connection.isClosed();
        } catch (Exception e) {
            return false;
        }
    }
}
'@ | Out-File -FilePath "src\scriptum\database\DatabaseConnection.java" -Encoding UTF8

Write-Host "   ✅ File Java minimal dibuat" -ForegroundColor Green

# 5. Coba compile
Write-Host "5. Mencoba compile..." -ForegroundColor Cyan
Remove-Item -Recurse -Force bin -ErrorAction SilentlyContinue
New-Item -ItemType Directory -Path bin -Force | Out-Null

javac -cp "lib\mysql-connector-java-8.0.28.jar" -d bin src\scriptum\Main.java src\scriptum\database\DatabaseConnection.java

if ($LASTEXITCODE -eq 0) {
    Write-Host "   ✅ Compile berhasil!" -ForegroundColor Green
    
    Write-Host ""
    Write-Host "=== SETUP SELESAI ===" -ForegroundColor Green
    Write-Host "Untuk menjalankan aplikasi:" -ForegroundColor Yellow
    Write-Host "java -cp ""bin;lib\mysql-connector-java-8.0.28.jar"" scriptum.Main" -ForegroundColor White
    
    # Jalankan otomatis
    Write-Host ""
    $run = Read-Host "Jalankan aplikasi sekarang? (y/n)"
    if ($run -eq 'y') {
        java -cp "bin;lib\mysql-connector-java-8.0.28.jar" scriptum.Main
    }
} else {
    Write-Host "   ❌ Compile gagal!" -ForegroundColor Red
}