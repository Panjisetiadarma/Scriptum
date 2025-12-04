# Script untuk compile Java project Scriptum

Write-Host "=== COMPILING SCRIPTUM APPLICATION ===" -ForegroundColor Green

# Hapus folder bin jika ada
if (Test-Path "bin") {
    Write-Host "Menghapus folder bin lama..." -ForegroundColor Yellow
    Remove-Item -Recurse -Force "bin"
}

# Buat folder bin baru
New-Item -ItemType Directory -Path "bin" -Force | Out-Null

# Cari semua file .java
Write-Host "Mencari semua file Java..." -ForegroundColor Cyan
$javaFiles = Get-ChildItem -Path "src" -Recurse -Filter "*.java" | Select-Object -ExpandProperty FullName

Write-Host "Found $($javaFiles.Count) Java files" -ForegroundColor Cyan

# Compile semua file
Write-Host "Compiling Java files..." -ForegroundColor Cyan
javac -cp "lib/mysql-connector-java-8.0.28.jar" -d bin $javaFiles

if ($LASTEXITCODE -eq 0) {
    Write-Host "✅ Compile berhasil!" -ForegroundColor Green
    Write-Host ""
    Write-Host "Untuk menjalankan aplikasi:" -ForegroundColor Yellow
    Write-Host "java -cp ""bin;lib/mysql-connector-java-8.0.28.jar"" scriptum.Main" -ForegroundColor White
} else {
    Write-Host "❌ Compile gagal!" -ForegroundColor Red
}