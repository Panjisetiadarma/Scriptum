@echo off
echo ========================================
echo    COMPILING SCRIPTUM APPLICATION
echo ========================================

REM Hapus folder bin jika ada
if exist bin rmdir /s /q bin

REM Buat folder bin
mkdir bin

REM Compile semua file Java
echo Compiling Java files...

REM Compile file per folder
javac -cp "lib\mysql-connector-java-8.0.28.jar" -d bin src\scriptum\Main.java
javac -cp "lib\mysql-connector-java-8.0.28.jar" -d bin src\scriptum\database\DatabaseConnection.java
javac -cp "lib\mysql-connector-java-8.0.28.jar" -d bin src\scriptum\models\*.java
javac -cp "lib\mysql-connector-java-8.0.28.jar" -d bin src\scriptum\controllers\*.java
javac -cp "lib\mysql-connector-java-8.0.28.jar" -d bin src\scriptum\utils\*.java
javac -cp "lib\mysql-connector-java-8.0.28.jar" -d bin src\scriptum\views\*.java

if errorlevel 1 (
    echo ❌ Compile gagal!
    pause
    exit /b 1
)

echo ✅ Compile berhasil!
echo.
echo Untuk menjalankan aplikasi:
echo java -cp "bin;lib\mysql-connector-java-8.0.28.jar" scriptum.Main
echo.
pause