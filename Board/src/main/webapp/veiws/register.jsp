<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<body>
    <h1>Register</h1>
    <form action="user" method="post">
        Username: <input type="text" name="username"><br>
        Password: <input type="password" name="password"><br>
        Email: <input type="text" name="email"><br>
        Nickname: <input type="text" name="nickname"><br>
        <input type="hidden" name="action" value="register">
        <input type="submit" value="Register">
    </form>
    <p>Already have an account? <a href="user?action=login">Login here</a></p>
</body>
</html>
