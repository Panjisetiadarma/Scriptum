-- Scriptum Library Management System Database Schema
-- MySQL Database

CREATE DATABASE IF NOT EXISTS library_db;
USE library_db;

-- Tabel User
CREATE TABLE IF NOT EXISTS user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    nama VARCHAR(100) NOT NULL,
    tgl_dftr DATE NOT NULL,
    status_lgn BOOLEAN DEFAULT FALSE,
    role ENUM('ADMIN', 'USER') DEFAULT 'USER'
);

-- Tabel Buku
CREATE TABLE IF NOT EXISTS buku (
    buku_id INT AUTO_INCREMENT PRIMARY KEY,
    judul VARCHAR(200) NOT NULL,
    penulis VARCHAR(100) NOT NULL,
    penerbit VARCHAR(100) NOT NULL,
    thn_terbit INT NOT NULL,
    ktegori VARCHAR(50) NOT NULL,
    stok INT DEFAULT 0
);

-- Tabel Peminjaman
CREATE TABLE IF NOT EXISTS peminjaman (
    id_peminjam INT AUTO_INCREMENT PRIMARY KEY,
    iser_id INT NOT NULL,
    buku_id INT NOT NULL,
    tgl_pinjm DATE NOT NULL,
    status_peminjaman VARCHAR(20) DEFAULT 'ACTIVE',
    jatuh_tempo DATE NOT NULL,
    FOREIGN KEY (iser_id) REFERENCES user(user_id) ON DELETE CASCADE,
    FOREIGN KEY (buku_id) REFERENCES buku(buku_id) ON DELETE CASCADE
);

-- Tabel Pengembalian
CREATE TABLE IF NOT EXISTS pengembalian (
    pengembalian_id INT AUTO_INCREMENT PRIMARY KEY,
    peminjaman_id INT NOT NULL,
    tanggal_kembali DATE NOT NULL,
    denda DECIMAL(10,2) DEFAULT 0.00,
    FOREIGN KEY (peminjaman_id) REFERENCES peminjaman(id_peminjam) ON DELETE CASCADE
);

-- Tabel Laporan
CREATE TABLE IF NOT EXISTS laporan (
    laporan_id INT AUTO_INCREMENT PRIMARY KEY,
    admin_id INT NOT NULL,
    tanggal_laporan DATE NOT NULL,
    jumlah_buku INT DEFAULT 0,
    jumlah_pinjaman INT DEFAULT 0,
    jumah_user_aktif INT DEFAULT 0,
    FOREIGN KEY (admin_id) REFERENCES user(user_id) ON DELETE CASCADE
);

-- Tabel Notifikasi
CREATE TABLE IF NOT EXISTS notifikasi (
    notifikasi_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    isi_pesan TEXT NOT NULL,
    tanggal_kirim DATE NOT NULL,
    status_baca BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);

-- Insert Data Sample
-- User Admin
INSERT INTO user (username, password, email, nama, tgl_dftr, status_lgn, role) 
VALUES ('admin', 'admin123', 'admin@scriptum.com', 'Administrator', CURDATE(), FALSE, 'ADMIN');

-- User Biasa
INSERT INTO user (username, password, email, nama, tgl_dftr, status_lgn, role) 
VALUES ('user1', 'user123', 'user1@scriptum.com', 'User Test', CURDATE(), FALSE, 'USER');

-- Sample Buku
INSERT INTO buku (judul, penulis, penerbit, thn_terbit, ktegori, stok) VALUES
('Pemrograman Java untuk Pemula', 'John Doe', 'Penerbit ABC', 2023, 'Teknologi', 10),
('Database Management System', 'Jane Smith', 'Penerbit XYZ', 2022, 'Teknologi', 5),
('Algoritma dan Struktur Data', 'Bob Johnson', 'Penerbit DEF', 2024, 'Teknologi', 8),
('Sejarah Indonesia', 'Ahmad Hidayat', 'Penerbit GHI', 2021, 'Sejarah', 15),
('Matematika Dasar', 'Siti Nurhaliza', 'Penerbit JKL', 2023, 'Pendidikan', 12);

