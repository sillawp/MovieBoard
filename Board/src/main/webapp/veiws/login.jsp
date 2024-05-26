<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>
    <form action="user" method="post">
        Username: <input type="text" name="username"><br>
        Password: <input type="password" name="password"><br>
        <input type="hidden" name="action" value="login">
        <input type="submit" value="Login">
    </form>
    <p>Don't have an account? <a href="user?action=register">Register here</a></p>
</body>
</html>
