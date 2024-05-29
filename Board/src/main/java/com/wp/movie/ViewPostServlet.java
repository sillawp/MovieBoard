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

public class ViewPostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewPostServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int postId = Integer.parseInt(request.getParameter("id"));

        String jdbcURL = "jdbc:mysql://localhost:3306/yourdatabase";
        String jdbcUsername = "yourusername";
        String jdbcPassword = "yourpassword";

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            String sql = "SELECT * FROM posts WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, postId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Post post = new Post(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("content"),
                    resultSet.getString("author"),
                    resultSet.getTimestamp("created_at")
                );

                request.setAttribute("post", post);
                request.getRequestDispatcher("/viewPost.jsp").forward(request, response);
            } else {
                response.sendRedirect("error.jsp"); // 에러 페이지로 리디렉션
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
