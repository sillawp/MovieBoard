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


public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection settings
    private static final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test"; // H2 데이터베이스 연결 URL
    private static final String JDBC_USER = "sa"; // H2 데이터베이스 사용자 이름
    private static final String JDBC_PASSWORD = ""; // H2 데이터베이스 암호

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 아이디와 비밀번호가 관리자 계정과 일치하는지 확인
        if (validateAdmin(username, password)) {
            // 관리자로 로그인 성공
            response.sendRedirect("adminDashboard.jsp");
        } else {
            // 일반 사용자로 로그인 시도
            if (validateUser(username, password)) {
                // 일반 사용자로 로그인 성공
                response.sendRedirect("MovieBoard.jsp");
            } else {
                // 로그인 실패
                response.sendRedirect("login.jsp?error=invalidCredentials");
            }
        }
    }

    // 관리자 계정인지 확인하는 메서드
    private boolean validateAdmin(String username, String password) {
        // 고정된 관리자 계정 정보
        String adminUsername = "admin";
        String adminPassword = "admin123";

        // 입력된 아이디와 비밀번호가 관리자 계정과 일치하는지 확인
        return adminUsername.equals(username) && adminPassword.equals(password);
    }

    // 일반 사용자 계정인지 확인하는 메서드
    private boolean validateUser(String username, String password) {
        boolean isValidUser = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // 데이터베이스 연결
            Class.forName("org.h2.Driver"); // H2 데이터베이스 드라이버 클래스 로드
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // SQL 쿼리
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // 사용자 정보가 존재하면 유효한 사용자
                isValidUser = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // 리소스 정리
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isValidUser;
    }
}
