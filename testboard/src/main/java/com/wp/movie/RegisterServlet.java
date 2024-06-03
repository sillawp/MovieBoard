package com.wp.movie;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");

        User newUser = new User(0, username, password, email, phone, gender); // 첫 번째 매개변수인 id는 0으로 설정하거나 다른 기본값을 사용합니다.

        try {
            userDAO.register(newUser);
            // 회원가입 성공 시 로그인 페이지로 이동
            response.sendRedirect("login.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            // 회원가입 실패 시 오류 메시지를 포함한 회원가입 페이지로 리다이렉트
            response.sendRedirect("register.jsp?error=registrationFailed");
        }
    }
}
