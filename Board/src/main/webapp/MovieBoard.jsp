<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.wp.movie.Post" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
</head>
<body>
    <h2>게시판</h2>
    <table border="1">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
        <%-- 게시글 목록 출력 --%>
        <%
            // Suppress the unchecked cast warning
            @SuppressWarnings("unchecked")
            List<Post> postList = (List<Post>) request.getAttribute("postList");
            if (postList != null) {
                for (Post post : postList) {
        %>
        <tr>
            <td><%= post.getId() %></td>
            <td><a href="viewPost?id=<%= post.getId() %>"><%= post.getTitle() %></a></td>
            <td><%= post.getAuthor() %></td>
            <td><%= post.getCreatedAt() %></td>
        </tr>
        <%      }
            }
        %>
        <%-- 게시글 목록 출력 종료 --%>
    </table>

    <!-- 게시글 쓰기 버튼 -->
    <div>
        <a href="writePost.jsp">게시글 쓰기</a>
    </div>
</body>
</html>
