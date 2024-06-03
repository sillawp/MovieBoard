package com.wp.movie;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ViewPostServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int postId = Integer.parseInt(request.getParameter("id"));

        // JDBC 연결 정보
        String jdbcURL = "jdbc:h2:~/test"; // H2 데이터베이스 연결 URL
        String jdbcUsername = "sa"; // H2 데이터베이스 사용자 이름
        String jdbcPassword = ""; // H2 데이터베이스 암호

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            String sql = "SELECT * FROM posts WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, postId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                    	Post post = new Post(
                    		    resultSet.getInt("id"),
                    		    resultSet.getString("title"),
                    		    resultSet.getString("author"),
                    		    resultSet.getTimestamp("created_at")
                    		);


                        request.setAttribute("post", post);
                        request.getRequestDispatcher("/viewPost.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("error.jsp"); // 에러 페이지로 리디렉션
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
