<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="scriptum.Buku" %>
<%@ page import="scriptum.User" %>
<%@ page import="java.util.List" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login");
        return;
    }
    List<Buku> books = (List<Buku>) request.getAttribute("books");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Daftar Buku - Scriptum</title>
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
        <div class="page-header">
            <h2>Daftar Buku</h2>
            <a href="books?action=add" class="btn-primary">Tambah Buku</a>
        </div>
        
        <div class="search-box">
            <form method="get" action="books">
                <input type="text" name="keyword" placeholder="Cari buku..." value="<%= request.getParameter("keyword") != null ? request.getParameter("keyword") : "" %>">
                <button type="submit" class="btn-secondary">Cari</button>
                <a href="books" class="btn-secondary">Reset</a>
            </form>
        </div>
        
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Judul</th>
                    <th>Penulis</th>
                    <th>Penerbit</th>
                    <th>Tahun Terbit</th>
                    <th>Kategori</th>
                    <th>Stok</th>
                    <th>Aksi</th>
                </tr>
            </thead>
            <tbody>
                <% if (books != null && !books.isEmpty()) { %>
                    <% for (Buku buku : books) { %>
                        <tr>
                            <td><%= buku.getBukuId() %></td>
                            <td><%= buku.getJudul() %></td>
                            <td><%= buku.getPenulis() %></td>
                            <td><%= buku.getPenerbit() %></td>
                            <td><%= buku.getThnTerbit() %></td>
                            <td><%= buku.getKtegori() %></td>
                            <td><%= buku.getStok() %></td>
                            <td>
                                <a href="books?action=edit&id=<%= buku.getBukuId() %>" class="btn-edit">Edit</a>
                                <a href="books?action=delete&id=<%= buku.getBukuId() %>" class="btn-delete" onclick="return confirm('Yakin ingin menghapus?')">Hapus</a>
                            </td>
                        </tr>
                    <% } %>
                <% } else { %>
                    <tr>
                        <td colspan="8" style="text-align: center;">Tidak ada data buku</td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>

