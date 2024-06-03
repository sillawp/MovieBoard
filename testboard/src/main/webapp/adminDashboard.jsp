<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wp.movie.User" %>
<%@ page import="com.wp.movie.UserDAO" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 대시보드</title>
    <!-- CSS 및 JavaScript 파일 링크 등 -->
</head>
<body>
    <h1>회원 목록</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>이름</th>
                <th>이메일</th>
                <th>전화번호</th>
                <th>성별</th>
            </tr>
        </thead>
        <tbody>
            <% 
                UserDAO userDAO = new UserDAO();
                List<User> userList = userDAO.getAllUsers();
                for (User user : userList) {
            %>
            <tr>
                <td><%= user.getId() %></td> <!-- getId() 메서드 호출 -->
                <td><%= user.getUsername() %></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.getPhone() %></td>
                <td><%= user.getGender() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
