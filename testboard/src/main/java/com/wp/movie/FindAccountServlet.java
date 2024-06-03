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
            // 유효하지 않은 요청 처리
            response.sendRedirect("error.jsp");
        }
    }

    private void findId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        String id = findIdByAccountInfo(phone, email);

        if (id != null) {
            // 아이디 일부만 표시하기 위한 처리
            String maskedId = maskId(id);
            request.setAttribute("message", "일치하는 계정이 있습니다. 아이디: " + maskedId);
        } else {
            request.setAttribute("message", "일치하는 계정이 없습니다.");
        }

        request.getRequestDispatcher("findAccount.jsp").forward(request, response);
    }

    private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        boolean success = resetPassword(username, email);

        if (success) {
            request.setAttribute("message", "비밀번호 재설정 이메일을 전송했습니다.");
            // 비밀번호 재설정 이메일 전송 로직
        } else {
            request.setAttribute("message", "일치하는 계정이 없습니다.");
        }

        request.getRequestDispatcher("findAccount.jsp").forward(request, response);
    }

 // 전화번호, 이메일로 아이디 찾기 메서드
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

    // 비밀번호 재설정 메서드
    private boolean resetPassword(String username, String email) {
        boolean success = false;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "UPDATE users SET password = ? WHERE username = ? AND email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                String newPassword = generateNewPassword(); // 새로운 비밀번호 생성
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

    // 새로운 비밀번호 생성 메서드
    private String generateNewPassword() {
        // 새로운 비밀번호를 생성하는 로직을 구현하여 반환합니다.
        return "newPassword"; // 임시로 새 비밀번호를 "newPassword"로 설정했습니다. 변경이 필요합니다.
    }


    // 아이디를 마스킹하여 일부만 표시하는 메서드
    private String maskId(String id) {
        int length = id.length();
        int visibleLength = Math.min(length, 5); // 처음 5자리만 보이도록 설정

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
