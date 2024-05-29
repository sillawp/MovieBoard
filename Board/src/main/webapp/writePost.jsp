<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>
</head>
<body>
    <h2>게시글 작성</h2>
    <form action="addPost" method="post">
        <div>
            <label>제목: </label>
            <input type="text" name="title" required>
        </div>
        <div>
            <label>내용: </label>
            <textarea name="content" required></textarea>
        </div>
        <div>
            <label>작성자: </label>
            <input type="text" name="author" required>
        </div>
        <div>
            <button type="submit">작성</button>
        </div>
    </form>
</body>
</html>
