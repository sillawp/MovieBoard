<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.wp.movie.Post" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 수정</title>
</head>
<body>
    <h2>게시글 수정</h2>
    <%
        Post post = (Post) request.getAttribute("post");
        if (post == null) {
            out.println("<p>해당 게시글을 찾을 수 없습니다.</p>");
        } else {
    %>
    <form action="editPost" method="post">
        <input type="hidden" name="id" value="<%= post.getId() %>">
        <div>
            <label>제목: </label>
            <input type="text" name="title" value="<%= post.getTitle() %>" required>
        </div>
        <div>
            <label>내용: </label>
            <textarea name="content" required><%= post.getContent() %></textarea>
        </div>
        <div>
            <button type="submit">저장</button>
        </div>
    </form>
    <%
        }
    %>
</body>
</html>
