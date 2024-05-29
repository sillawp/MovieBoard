<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.wp.movie.Post" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 보기</title>
</head>
<body>
    <%
        Post post = (Post) request.getAttribute("post");
        if (post == null) {
            out.println("<p>해당 게시글을 찾을 수 없습니다.</p>");
        } else {
    %>
    <h2><%= post.getTitle() %></h2>
    <p><%= post.getContent() %></p>
    <p>작성자: <%= post.getAuthor() %></p>
    <p>작성일: <%= post.getCreatedAt() %></p>
    
    <form action="editPost" method="get">
        <input type="hidden" name="id" value="<%= post.getId() %>">
        <button type="submit">수정하기</button>
    </form>
    
    <form action="deletePost" method="post" onsubmit="return confirm('정말로 삭제하시겠습니까?');">
        <input type="hidden" name="id" value="<%= post.getId() %>">
        <button type="submit">삭제하기</button>
    </form>
    <%
        }
    %>
</body>
</html>
