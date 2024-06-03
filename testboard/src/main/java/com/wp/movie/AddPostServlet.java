package com.wp.movie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ��û���κ��� �Խñ��� ����� ������ �����ɴϴ�.
        String title = request.getParameter("title");
        String content = request.getParameter("detail"); // textarea�� name�� "detail"�� �����߽��ϴ�.

        // ����� �ۼ��ڸ� ���������� �����ϰų� ���� ���� ���� �α����� ����� ������ �����ͼ� ����ؾ� �մϴ�.
        String author = "�ۼ���"; // �ۼ��� ������ �޾ƿ��� ����� ������Ʈ�� ������ ���� �ٸ� �� �ֽ��ϴ�.

        // ������ ������ �̿��Ͽ� ���ο� �Խñ� ��ü�� �����մϴ�.
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);

        // PostDAO�� ���� �Խñ��� �����ͺ��̽��� �߰��մϴ�.
        PostDAO postDAO = new PostDAO();
        postDAO.addPost(post);

        // �Խñ� ��� �Ŀ��� viewPost.jsp �������� �̵��մϴ�.
        response.sendRedirect("viewPost.jsp?id=" + post.getId());
    }
}
