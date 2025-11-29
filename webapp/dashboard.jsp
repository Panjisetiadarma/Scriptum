<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="scriptum.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Scriptum</title>
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
        <h2>Dashboard</h2>
        
        <div class="dashboard-cards">
            <div class="card">
                <h3>Selamat Datang</h3>
                <p><strong>Nama:</strong> <%= user.getNama() %></p>
                <p><strong>Email:</strong> <%= user.getEmail() %></p>
                <p><strong>Role:</strong> <%= user.getRole() %></p>
            </div>
            
            <div class="card">
                <h3>Quick Actions</h3>
                <a href="books" class="btn-primary">Kelola Buku</a>
                <a href="books?action=add" class="btn-secondary">Tambah Buku</a>
            </div>
        </div>
    </div>
</body>
</html>

