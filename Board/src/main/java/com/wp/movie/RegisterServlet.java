package com.wp.movie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;


public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");

        User newUser = new User(username, password, email, phone, gender);

        try {
            userDAO.register(newUser);
            // 회원가입 성공 시 로그인 페이지로 이동
            response.sendRedirect("login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            // 회원가입 실패 시 오류 메시지를 포함한 회원가입 페이지로 리다이렉트
            response.sendRedirect("register.jsp?error=registrationFailed");
        }
    }
}
