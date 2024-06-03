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
            // �̸��� ���� �ڵ�
            // EmailSender emailSender = new EmailSender("your_email@gmail.com", "your_password");
            // emailSender.sendEmail(email, "���̵� ã�� ���", "����� ���̵�� " + id + "�Դϴ�.");
            request.setAttribute("message", "���̵� ã�� ����� �̸��Ϸ� �����߽��ϴ�.");
        } else {
            request.setAttribute("message", "��ġ�ϴ� �̸����� �����ϴ�.");
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
