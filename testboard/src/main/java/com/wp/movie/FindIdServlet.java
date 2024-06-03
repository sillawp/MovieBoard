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

public class FindIdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        String id = findIdByEmail(email);

        if (id != null) {
            // 이메일 전송 코드
            // EmailSender emailSender = new EmailSender("your_email@gmail.com", "your_password");
            // emailSender.sendEmail(email, "아이디 찾기 결과", "당신의 아이디는 " + id + "입니다.");
            request.setAttribute("message", "아이디 찾기 결과를 이메일로 전송했습니다.");
        } else {
            request.setAttribute("message", "일치하는 이메일이 없습니다.");
        }

        request.getRequestDispatcher("findAccount.jsp").forward(request, response);
    }

    private String findIdByEmail(String email) {
        String id = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "SELECT username FROM users WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        id = resultSet.getString("username");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
