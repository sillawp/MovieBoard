<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Page</title>
</head>
<body>
    <h1>Admin Page</h1>
    <h2>User Management</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Nickname</th>
            <th>Delete</th>
        </tr>
        <!-- Iterate over users and display information -->
        <%-- Example:
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.nickname}</td>
                <td><a href="admin?action=deleteUser&id=${user.id}">Delete</a></td>
            </tr>
        </c:forEach>
        --%>
    </table>
    <h2>Post Management</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Created At</th>
            <th>Delete</th>
        </tr>
        <!-- Iterate over posts and display information -->
        <%-- Example:
        <c:forEach var="post" items="${posts}">
            <tr>
                <td>${post.id}</td>
                <td>${post.title}</td>
                <td>${post.author}</td>
                <td>${post.createdAt}</td>
                <td><a href="admin?action=deletePost&id=${post.id}">Delete</a></td>
            </tr>
        </c:forEach>
        --%>
    </table>
</body>
</html>
