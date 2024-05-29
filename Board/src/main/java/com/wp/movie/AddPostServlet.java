package com.wp.movie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 게시글 작성 페이지에서 제목, 내용, 작성자 정보를 받아옴
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String author = request.getParameter("author");

        // JDBC 연결 정보
        String jdbcURL = "jdbc:mysql://localhost:3306/yourdatabase";
        String jdbcUsername = "yourusername";
        String jdbcPassword = "yourpassword";

        // SQL INSERT 쿼리
        String sql = "INSERT INTO posts (title, content, author) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // SQL 쿼리에 값을 설정
            statement.setString(1, title);
            statement.setString(2, content);
            statement.setString(3, author);

            // SQL 쿼리 실행하여 데이터베이스에 게시글 저장
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 게시판 페이지로 리다이렉트
        response.sendRedirect("board");
    }
}
