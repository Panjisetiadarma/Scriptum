@echo off
echo ========================================
echo Memperbaiki Dependencies untuk VS Code
echo ========================================
echo.

echo [1/4] Membersihkan build sebelumnya...
call mvn clean
echo.

echo [2/4] Download semua dependencies...
call mvn dependency:resolve
call mvn dependency:sources
echo.

echo [3/4] Compile project...
call mvn compile
echo.

echo [4/4] Install ke local repository...
call mvn install
echo.

echo ========================================
echo Selesai! Sekarang:
echo 1. Reload Window di VS Code (Ctrl+Shift+P -^> Reload Window)
echo 2. Atau restart VS Code
echo ========================================
pause

