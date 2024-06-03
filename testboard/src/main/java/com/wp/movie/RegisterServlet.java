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

        User newUser = new User(0, username, password, email, phone, gender); // ù ��° �Ű������� id�� 0���� �����ϰų� �ٸ� �⺻���� ����մϴ�.

        try {
            userDAO.register(newUser);
            // ȸ������ ���� �� �α��� �������� �̵�
            response.sendRedirect("login.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            // ȸ������ ���� �� ���� �޽����� ������ ȸ������ �������� �����̷�Ʈ
            response.sendRedirect("register.jsp?error=registrationFailed");
        }
    }
}
