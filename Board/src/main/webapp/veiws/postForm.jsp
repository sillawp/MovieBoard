<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create/Edit Post</title>
</head>
<body>
    <h1>Create/Edit Post</h1>
    <form action="post" method="post">
        Title: <input type="text" name="title" value="${post.title}"><br>
        Content: <textarea name="content">${post.content}</textarea><br>
        <input type="hidden" name="id" value="${post.id}">
        <input type="hidden" name="action" value="${action}">
        <input type="submit" value="Submit">
    </form>
</body>
</html>
