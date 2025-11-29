# Scriptum Library Management System

Sistem manajemen perpustakaan berbasis web menggunakan Java Servlet dan JSP.

## 📋 Fitur

- ✅ Login dan Logout
- ✅ Dashboard untuk Admin dan User
- ✅ Manajemen Buku (CRUD)
- ✅ Pencarian Buku
- ✅ Session Management

## 🛠️ Teknologi yang Digunakan

- **Java 11**
- **Java Servlet & JSP**
- **MySQL Database**
- **Maven** (untuk dependency management)
- **Tomcat** (Web Server)

## 📦 Prasyarat

Sebelum menjalankan aplikasi, pastikan Anda telah menginstall:

1. **Java JDK 11 atau lebih tinggi**
   - Download dari: https://www.oracle.com/java/technologies/downloads/
   - Atau OpenJDK: https://adoptium.net/

2. **Apache Tomcat 9.0 atau lebih tinggi**
   - Download dari: https://tomcat.apache.org/download-90.cgi

3. **MySQL Server 8.0 atau lebih tinggi**
   - Download dari: https://dev.mysql.com/downloads/mysql/

4. **Maven 3.6 atau lebih tinggi** (opsional, jika menggunakan IDE seperti Eclipse/IntelliJ)
   - Download dari: https://maven.apache.org/download.cgi

5. **IDE/Editor** (pilih salah satu):
   - **Visual Studio Code** (Direkomendasikan - ringan dan mudah)
     - Download dari: https://code.visualstudio.com/
     - Install extension: Extension Pack for Java
   - **Eclipse IDE for Enterprise Java and Web Developers**
     - Download dari: https://www.eclipse.org/downloads/
   - **IntelliJ IDEA**
     - Download dari: https://www.jetbrains.com/idea/
   - **NetBeans**
     - Download dari: https://netbeans.apache.org/

## 🗄️ Setup Database

1. **Buat Database MySQL:**
   ```sql
   CREATE DATABASE library_db;
   USE library_db;
   ```

2. **Jalankan script SQL** yang ada di file `database/schema.sql` untuk membuat tabel-tabel yang diperlukan.

3. **Update konfigurasi database** di file `src/scriptum/DatabaseConnection.java`:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/library_db";
   private static final String USERNAME = "root";  // ganti dengan username MySQL Anda
   private static final String PASSWORD = "password";  // ganti dengan password MySQL Anda
   ```

## 🚀 Cara Menjalankan Aplikasi

### Metode 1: Menggunakan Visual Studio Code (Direkomendasikan)

Visual Studio Code adalah editor yang ringan dan mudah digunakan. Berikut langkah-langkah untuk menjalankan aplikasi:

#### 1. Install Extension yang Diperlukan

Buka VS Code dan install extension berikut melalui Extensions (Ctrl+Shift+X):

- **Extension Pack for Java** (oleh Microsoft)
  - Termasuk: Language Support for Java, Debugger for Java, Test Runner for Java, Maven for Java, dll
  - Atau install secara individual:
    - `Extension Pack for Java`
    - `Maven for Java`
    - `Tomcat for Java` (opsional, untuk integrasi Tomcat)

#### 2. Buka Project di VS Code

1. Buka VS Code
2. File → Open Folder
3. Pilih folder project `Scriptum`
4. VS Code akan otomatis mendeteksi project Maven

#### 3. Download Dependencies

**Opsi A: Menggunakan Script (Paling Mudah)**

1. **Windows:** Double-click file `fix-dependencies.bat`
2. **Linux/Mac:** Jalankan di terminal:
   ```bash
   chmod +x fix-dependencies.sh
   ./fix-dependencies.sh
   ```

**Opsi B: Manual di Terminal**

1. Buka terminal di VS Code (Ctrl + ` atau Terminal → New Terminal)
2. Jalankan perintah untuk download dependencies:
   ```bash
   mvn clean install
   ```
3. Tunggu hingga semua dependencies terdownload (termasuk MySQL Connector, Servlet API, dll)
4. **PENTING:** Setelah selesai, reload window VS Code:
   - Tekan `Ctrl+Shift+P` (atau `Cmd+Shift+P` di Mac)
   - Ketik: `Developer: Reload Window`
   - Tekan Enter

#### 4. Setup Tomcat Server

**Opsi A: Menggunakan Tomcat Extension (Jika Terinstall)**

1. Install extension **"Tomcat for Java"** dari marketplace
2. Klik ikon Tomcat di sidebar (atau Ctrl+Shift+P → "Tomcat: Add Tomcat Server")
3. Pilih lokasi instalasi Tomcat Anda
4. Klik kanan pada server → "Add War Package"
5. Pilih file `target/Scriptum.war` (atau build terlebih dahulu dengan `mvn clean package`)
6. Klik kanan pada server → "Start"

**Opsi B: Build dan Deploy Manual (Lebih Mudah)**

1. **Build WAR file:**
   ```bash
   mvn clean package
   ```
   File WAR akan dibuat di folder `target/Scriptum.war`

2. **Deploy ke Tomcat:**
   - Copy file `target/Scriptum.war` ke folder `webapps` di instalasi Tomcat
   - Atau copy folder `webapp` ke `webapps/Scriptum` di instalasi Tomcat

3. **Start Tomcat Server:**
   
   **Windows:**
   ```bash
   # Buka Command Prompt atau PowerShell
   cd C:\path\to\tomcat\bin
   catalina.bat start
   ```
   
   **Linux/Mac:**
   ```bash
   cd /path/to/tomcat/bin
   ./catalina.sh start
   ```

4. **Akses Aplikasi:**
   - Buka browser dan akses: `http://localhost:8080/Scriptum`
   - Atau `http://localhost:8080/Scriptum/login`

#### 5. Development Workflow di VS Code

**Untuk Development (Edit Code):**

1. Edit file Java di folder `src/scriptum/`
2. Edit file JSP di folder `webapp/`
3. Setelah edit, build ulang:
   ```bash
   mvn clean package
   ```
4. Restart Tomcat atau reload aplikasi

**Tips:**
- Gunakan **Java Extension Pack** untuk autocomplete dan error detection
- Gunakan **Live Server** extension (opsional) untuk preview JSP (tapi tidak akan bisa menjalankan servlet)
- Untuk debugging, gunakan **Debugger for Java** extension

#### 6. Troubleshooting di VS Code

**Error: "Cannot resolve servlet API"**
- Normal terjadi, karena servlet API disediakan oleh Tomcat saat runtime
- Untuk menghilangkan error di editor, tambahkan servlet-api.jar ke classpath:
  1. Download `servlet-api.jar` dari folder `lib` di instalasi Tomcat
  2. Buat folder `lib` di root project
  3. Copy `servlet-api.jar` ke folder `lib`
  4. Update `pom.xml` untuk include local jar (atau biarkan saja, error akan hilang saat runtime)

**Error: "Maven not found"**
- Install Maven terlebih dahulu
- Atau gunakan Maven wrapper yang disediakan extension
- Pastikan Maven ada di PATH environment variable

**Tomcat tidak start**
- Pastikan port 8080 tidak digunakan aplikasi lain
- Check log di folder `logs` di instalasi Tomcat
- Pastikan Java sudah terinstall dan terdeteksi

### Metode 2: Menggunakan Eclipse IDE

1. **Import Project:**
   - Buka Eclipse IDE
   - File → Import → Existing Projects into Workspace
   - Pilih folder project Scriptum
   - Klik Finish

2. **Setup Tomcat Server:**
   - Window → Show View → Servers
   - Klik kanan → New → Server
   - Pilih Apache → Tomcat v9.0 Server
   - Pilih lokasi instalasi Tomcat Anda
   - Klik Finish

3. **Deploy Project:**
   - Klik kanan pada project → Properties
   - Project Facets → centang "Dynamic Web Project" dan "Java"
   - Klik kanan pada project → Build Path → Configure Build Path
   - Libraries → Add External JARs → pilih servlet-api.jar dan jsp-api.jar dari folder lib Tomcat

4. **Run Project:**
   - Klik kanan pada project → Run As → Run on Server
   - Pilih Tomcat server yang sudah dikonfigurasi
   - Aplikasi akan otomatis di-deploy dan browser akan terbuka

### Metode 3: Menggunakan Maven dan Tomcat Manual

1. **Build WAR file:**
   ```bash
   mvn clean package
   ```
   File WAR akan dibuat di folder `target/Scriptum.war`

2. **Deploy ke Tomcat:**
   - Copy file `target/Scriptum.war` ke folder `webapps` di instalasi Tomcat
   - Atau copy folder `webapp` ke `webapps/Scriptum` di instalasi Tomcat

3. **Start Tomcat:**
   ```bash
   # Windows
   catalina.bat start
   
   # Linux/Mac
   ./catalina.sh start
   ```

4. **Akses Aplikasi:**
   - Buka browser dan akses: `http://localhost:8080/Scriptum`

### Metode 4: Menggunakan IntelliJ IDEA

1. **Import Project:**
   - File → Open → Pilih folder project Scriptum
   - Pilih "Import project from external model" → Maven
   - Klik Next hingga Finish

2. **Setup Tomcat:**
   - Run → Edit Configurations
   - Klik + → Tomcat Server → Local
   - Configure Tomcat installation
   - Deployment tab → Add → Artifact → pilih Scriptum:war
   - Klik OK

3. **Run:**
   - Klik tombol Run atau tekan Shift+F10

## 📝 Default Login

Setelah setup database, buat user admin dengan menjalankan query berikut:

```sql
INSERT INTO user (username, password, email, nama, tgl_dftr, status_lgn, role) 
VALUES ('admin', 'admin123', 'admin@scriptum.com', 'Administrator', CURDATE(), false, 'ADMIN');
```

**Default Login:**
- Username: `admin`
- Password: `admin123`

## 📁 Struktur Project

```
Scriptum/
├── src/
│   └── scriptum/
│       ├── User.java
│       ├── Buku.java
│       ├── Peminjaman.java
│       ├── Pengembalian.java
│       ├── Laporan.java
│       ├── Notifikasi.java
│       ├── DatabaseConnection.java
│       ├── UserDAO.java
│       ├── BookDAO.java
│       ├── AuthService.java
│       ├── LoginServlet.java
│       ├── LogoutServlet.java
│       ├── BookServlet.java
│       └── DashboardServlet.java
├── webapp/
│   ├── WEB-INF/
│   │   └── web.xml
│   ├── css/
│   │   └── style.css
│   ├── index.jsp
│   ├── login.jsp
│   ├── dashboard.jsp
│   ├── books.jsp
│   ├── addBook.jsp
│   └── editBook.jsp
├── pom.xml
└── README.md
```

## 🔧 Troubleshooting

### Error: Servlet API tidak ditemukan (di IDE)
**Solusi:** 
- **VS Code:** 
  1. Jalankan script `fix-dependencies.bat` (Windows) atau `fix-dependencies.sh` (Linux/Mac)
  2. Atau jalankan: `mvn clean install` di terminal
  3. **PENTING:** Reload window VS Code (`Ctrl+Shift+P` → `Developer: Reload Window`)
  4. Jika masih error, tekan `Ctrl+Shift+P` → `Java: Clean Java Language Server Workspace` → `Java: Reload Projects`
  5. Lihat file `CARA_MEMPERBAIKI_ERROR.md` untuk solusi lengkap
- **Eclipse:** Klik kanan project → Properties → Java Build Path → Add External JARs → pilih `servlet-api.jar` dan `jsp-api.jar` dari folder `lib` di instalasi Tomcat
- **IntelliJ:** File → Project Structure → Libraries → Add → pilih folder `lib` dari instalasi Tomcat

### Error: ClassNotFoundException untuk MySQL Driver
**Solusi:** Pastikan dependency MySQL Connector sudah terdownload. Jalankan:
```bash
mvn clean install
```

### Error: Database Connection Failed
**Solusi:** 
- Pastikan MySQL server sedang berjalan
- Periksa username dan password di `DatabaseConnection.java`
- Pastikan database `library_db` sudah dibuat
- Jalankan script SQL di `database/schema.sql`

### Error: 404 Not Found
**Solusi:**
- Pastikan context path benar (biasanya `/Scriptum`)
- Periksa file `web.xml` sudah benar
- Pastikan semua servlet sudah terdaftar di `web.xml`
- Pastikan folder `webapp` sudah benar di struktur project

### Port 8080 sudah digunakan
**Solusi:**
- Ganti port di file `server.xml` di folder `conf` Tomcat
- Atau stop aplikasi lain yang menggunakan port 8080

## 📚 Endpoint yang Tersedia

- `/` atau `/index.jsp` - Redirect ke login
- `/login` - Halaman login
- `/logout` - Logout user
- `/dashboard` - Dashboard user
- `/books` - Daftar buku
- `/books?action=add` - Form tambah buku
- `/books?action=edit&id={id}` - Form edit buku
- `/books?action=delete&id={id}` - Hapus buku
- `/books?keyword={keyword}` - Pencarian buku

## 👨‍💻 Developer

Project ini dibuat untuk pembelajaran Java OOP dan Web Development.

## 📄 License

Project ini dibuat untuk keperluan edukasi.

---

**Selamat menggunakan Scriptum Library Management System!** 🎉

