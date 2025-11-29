<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="scriptum.User" %>
<%@ page import="scriptum.Buku" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login");
        return;
    }
    Buku buku = (Buku) request.getAttribute("buku");
    if (buku == null) {
        response.sendRedirect("books");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Buku - Scriptum</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <nav class="navbar">
        <div class="nav-container">
            <h1>Scriptum Library</h1>
            <div class="nav-links">
                <a href="dashboard">Dashboard</a>
                <a href="books">Buku</a>
                <span>Welcome, <%= user.getNama() %> (<%= user.getRole() %>)</span>
                <a href="logout">Logout</a>
            </div>
        </div>
    </nav>
    
    <div class="container">
        <h2>Edit Buku</h2>
        
        <form method="post" action="books" class="form-container">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="bukuId" value="<%= buku.getBukuId() %>">
            
            <div class="form-group">
                <label for="judul">Judul:</label>
                <input type="text" id="judul" name="judul" value="<%= buku.getJudul() %>" required>
            </div>
            
            <div class="form-group">
                <label for="penulis">Penulis:</label>
                <input type="text" id="penulis" name="penulis" value="<%= buku.getPenulis() %>" required>
            </div>
            
            <div class="form-group">
                <label for="penerbit">Penerbit:</label>
                <input type="text" id="penerbit" name="penerbit" value="<%= buku.getPenerbit() %>" required>
            </div>
            
            <div class="form-group">
                <label for="thnTerbit">Tahun Terbit:</label>
                <input type="number" id="thnTerbit" name="thnTerbit" value="<%= buku.getThnTerbit() %>" required>
            </div>
            
            <div class="form-group">
                <label for="ktegori">Kategori:</label>
                <input type="text" id="ktegori" name="ktegori" value="<%= buku.getKtegori() %>" required>
            </div>
            
            <div class="form-group">
                <label for="stok">Stok:</label>
                <input type="number" id="stok" name="stok" value="<%= buku.getStok() %>" required min="0">
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn-primary">Update</button>
                <a href="books" class="btn-secondary">Batal</a>
            </div>
        </form>
    </div>
</body>
</html>

