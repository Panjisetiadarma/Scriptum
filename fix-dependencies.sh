#!/bin/bash

echo "========================================"
echo "Memperbaiki Dependencies untuk VS Code"
echo "========================================"
echo ""

echo "[1/4] Membersihkan build sebelumnya..."
mvn clean
echo ""

echo "[2/4] Download semua dependencies..."
mvn dependency:resolve
mvn dependency:sources
echo ""

echo "[3/4] Compile project..."
mvn compile
echo ""

echo "[4/4] Install ke local repository..."
mvn install
echo ""

echo "========================================"
echo "Selesai! Sekarang:"
echo "1. Reload Window di VS Code (Cmd+Shift+P -> Reload Window)"
echo "2. Atau restart VS Code"
echo "========================================"

