<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wp.movie.Post" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시판</title>
<style>
    table {
        margin: auto;
        width: 90%;
        border-radius: 5px;
        border-collapse: collapse;
    }
    .header {
        background-color: #f2f2f2;
        text-align: center;
    }
    th, td {
        border-bottom: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }
    tr:nth-child(even) {
        background-color: #f2f2f2;
    }
    tr:hover {
        background-color: #ddd;
    }
    .num {
        width: 50px;
    }
    .title {
        width: 500px;
        cursor: pointer; /* 마우스를 올리면 커서 모양 변경 */
    }
    .body {
        text-align: center;
    }
    .body .title {
        text-align: left;
    }
    .button-container {
        text-align: center;
        margin-top: 20px;
    }
    .button-container button {
        width: 100px;
        height: 40px;
        font-size: 15px;
        border: none;
        outline: none;
        border-radius: 5px;
        background-color: #4CAF50;
        color: white;
        cursor: pointer;
    }
    .button-container button:hover {
        background-color: #45a049;
    }
</style>
</head>
<body>
<table>
    <tr class="header">
        <th class="num">번호</th>
        <th class="title">제목</th>
        <th>작성자</th>
        <th>작성날짜</th>
    </tr>
    <% 
        // 여기서부터 Java 코드로 게시글 목록을 가져와서 출력합니다.
        List<Post> postList = (List<Post>) request.getAttribute("postList");
        if (postList != null) {
            int i = postList.size();
            for (Post post : postList) {
    %>
    <tr class="body">
        <td><%= i-- %></td>
        <td class="title"><a href="viewPost.jsp?id=<%= post.getId() %>"><%= post.getTitle() %></a></td>
        <td><%= post.getAuthor() %></td>
        <td><%= post.getCreatedAt() %></td>
    </tr>
    <% 
            }
        }
    %>
</table>
<div class="button-container">
    <button onclick="location.href='writePost.jsp'">글쓰기</button>
</div>
</body>
</html>
