# APLIKASI SCRIPTUM

Sistem Manajemen Peminjaman Lemari berbasis Java dengan konsep OOP.

## 🚀 FITUR UTAMA

✅ **Login System** - Multi user (Admin & User)  
✅ **Dashboard** - Tampilan berbeda berdasarkan role  
✅ **Peminjaman** - Form pengajuan peminjaman lemari  
✅ **Laporan** - View riwayat peminjaman  
✅ **Notifikasi** - Sistem notifikasi real-time  
✅ **Database** - MySQL dengan 5 tabel terstruktur  

## 🛠️ TEKNOLOGI

- Java 8+
- MySQL Database
- Java Swing (GUI)
- JDBC (Database Connection)

## 📦 INSTALASI

### 1. Prasyarat
- Java Development Kit (JDK) 8+
- MySQL Server 5.7+
- IDE (Eclipse/IntelliJ/NetBeans)

### 2. Setup Database
```sql
-- Buat database
CREATE DATABASE scriptum_db;

-- Import file database/scriptum_db.sql
mysql -u root -p scriptum_db < database/scriptum_db.sql