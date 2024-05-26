<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Post List</title>
</head>
<body>
    <h1>Post List</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Created At</th>
            <th>Actions</th>
        </tr>
        <!-- Iterate over posts and display information -->
        <%-- Example:
        <c:forEach var="post" items="${posts}">
            <tr>
                <td>${post.id}</td>
                <td>${post.title}</td>
                <td>${post.author}</td>
                <td>${post.createdAt}</td>
                <td><a href="post?action=detail&id=${post.id}">Detail</a> | <a href="post?action=edit&id=${post.id}">Edit</a> | <a href="post?action=delete&id=${post.id}">Delete</a></td>
            </tr>
        </c:forEach>
        --%>
    </table>
</body>
</html>
