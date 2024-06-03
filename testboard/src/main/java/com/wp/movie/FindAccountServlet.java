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

public class FindAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test"; 
    private static final String JDBC_USER = "sa"; 
    private static final String JDBC_PASSWORD = ""; 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("findId".equals(action)) {
            findId(request, response);
        } else if ("resetPassword".equals(action)) {
            resetPassword(request, response);
        } else {
            // ��ȿ���� ���� ��û ó��
            response.sendRedirect("error.jsp");
        }
    }

    private void findId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        String id = findIdByAccountInfo(phone, email);

        if (id != null) {
            // ���̵� �Ϻθ� ǥ���ϱ� ���� ó��
            String maskedId = maskId(id);
            request.setAttribute("message", "��ġ�ϴ� ������ �ֽ��ϴ�. ���̵�: " + maskedId);
        } else {
            request.setAttribute("message", "��ġ�ϴ� ������ �����ϴ�.");
        }

        request.getRequestDispatcher("findAccount.jsp").forward(request, response);
    }

    private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        boolean success = resetPassword(username, email);

        if (success) {
            request.setAttribute("message", "��й�ȣ �缳�� �̸����� �����߽��ϴ�.");
            // ��й�ȣ �缳�� �̸��� ���� ����
        } else {
            request.setAttribute("message", "��ġ�ϴ� ������ �����ϴ�.");
        }

        request.getRequestDispatcher("findAccount.jsp").forward(request, response);
    }

 // ��ȭ��ȣ, �̸��Ϸ� ���̵� ã�� �޼���
    private String findIdByAccountInfo(String phone, String email) {
        String id = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "SELECT username FROM users WHERE phone = ? AND email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, phone);
                preparedStatement.setString(2, email);
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

    // ��й�ȣ �缳�� �޼���
    private boolean resetPassword(String username, String email) {
        boolean success = false;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "UPDATE users SET password = ? WHERE username = ? AND email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                String newPassword = generateNewPassword(); // ���ο� ��й�ȣ ����
                preparedStatement.setString(1, newPassword);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, email);
                int rowsUpdated = preparedStatement.executeUpdate();
                success = rowsUpdated > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    // ���ο� ��й�ȣ ���� �޼���
    private String generateNewPassword() {
        // ���ο� ��й�ȣ�� �����ϴ� ������ �����Ͽ� ��ȯ�մϴ�.
        return "newPassword"; // �ӽ÷� �� ��й�ȣ�� "newPassword"�� �����߽��ϴ�. ������ �ʿ��մϴ�.
    }


    // ���̵� ����ŷ�Ͽ� �Ϻθ� ǥ���ϴ� �޼���
    private String maskId(String id) {
        int length = id.length();
        int visibleLength = Math.min(length, 5); // ó�� 5�ڸ��� ���̵��� ����

        StringBuilder maskedId = new StringBuilder();
        for (int i = 0; i < visibleLength; i++) {
            maskedId.append(id.charAt(i));
        }
        for (int i = visibleLength; i < length; i++) {
            maskedId.append('*');
        }

        return maskedId.toString();
    }
}
