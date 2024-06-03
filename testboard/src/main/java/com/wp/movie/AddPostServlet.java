package com.wp.movie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청으로부터 게시글의 제목과 내용을 가져옵니다.
        String title = request.getParameter("title");
        String content = request.getParameter("detail"); // textarea의 name을 "detail"로 지정했습니다.

        // 현재는 작성자를 고정적으로 설정하거나 세션 등을 통해 로그인한 사용자 정보를 가져와서 사용해야 합니다.
        String author = "작성자"; // 작성자 정보를 받아오는 방법은 프로젝트의 구성에 따라 다를 수 있습니다.

        // 가져온 정보를 이용하여 새로운 게시글 객체를 생성합니다.
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);

        // PostDAO를 통해 게시글을 데이터베이스에 추가합니다.
        PostDAO postDAO = new PostDAO();
        postDAO.addPost(post);

        // 게시글 등록 후에는 viewPost.jsp 페이지로 이동합니다.
        response.sendRedirect("viewPost.jsp?id=" + post.getId());
    }
}
