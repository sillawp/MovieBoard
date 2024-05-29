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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BoardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        // 게시글 목록을 저장할 리스트
        List<Post> postList = new ArrayList<>();

        // JDBC 연결 정보
        String jdbcURL = "jdbc:mysql://localhost:3306/yourdatabase";
        String jdbcUsername = "yourusername";
        String jdbcPassword = "yourpassword";

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            // SQL 쿼리 실행하여 모든 게시글 가져오기
            String sql = "SELECT * FROM posts ORDER BY created_at DESC";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    Timestamp createdAt = resultSet.getTimestamp("created_at");

                    // 가져온 데이터를 Post 객체에 저장 후 리스트에 추가
                    Post post = new Post(id, title, author, createdAt);
                    postList.add(post);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 게시판 페이지로 게시글 목록을 전달하여 포워딩
        request.setAttribute("postList", postList);
        request.getRequestDispatcher("/MovieBoard.jsp").forward(request, response);
    }
}
