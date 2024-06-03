<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.wp.movie.Post" %>
<%@ page import="com.wp.movie.User" %>
<%@ page import="com.wp.movie.PostDAO" %>

<%
// 현재 로그인한 사용자의 정보를 확인하는 로직
User currentUser = (User) session.getAttribute("user");
PostDAO postDAO = new PostDAO();
Post post = null;
boolean canEditOrDelete = false;

// 현재 사용자 정보가 있고, 사용자가 관리자인지 확인
if (currentUser != null) {
    boolean isAdmin = currentUser.isAdmin();
    int postId = Integer.parseInt(request.getParameter("id"));
    post = postDAO.getPostById(postId);
    
    // 현재 사용자가 글의 작성자인지 확인
    boolean isAuthor = currentUser.getUsername().equals(post.getAuthor());
    canEditOrDelete = isAdmin || isAuthor;
}
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>글 상세보기</title>
    <!-- CSS 및 JavaScript 파일 링크 등 -->
</head>
<body>
    <!-- 게시글 내용 표시 -->
    <div>
        <h2><%= (post != null) ? post.getTitle() : "" %></h2>
        <p>작성자: <%= (post != null) ? post.getAuthor() : "" %></p>
        <p>작성일: <%= (post != null) ? post.getCreatedAt() : "" %></p>
        <p><%= (post != null) ? post.getContent() : "" %></p>
    </div>

    <!-- 수정 및 삭제 버튼 표시 -->
    <div>
        <% if (canEditOrDelete) { %>
            <!-- 수정 버튼 -->
            <form action="editPost.jsp" method="get">
                <input type="hidden" name="id" value="<%= (post != null) ? post.getId() : "" %>">
                <input type="submit" value="수정">
            </form>

            <!-- 삭제 버튼 -->
            <form action="deletePost.jsp" method="post">
                <input type="hidden" name="id" value="<%= (post != null) ? post.getId() : "" %>">
                <input type="submit" value="삭제">
            </form>
        <% } %>
    </div>
</body>
</html>
