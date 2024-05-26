package com.wp.controller;

import com.wp.dao.PostDao;
import com.wp.model.Post;
import com.wp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/post")
@MultipartConfig
public class PostController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PostDao postDao = new PostDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equals(action)) {
            listPosts(request, response);
        } else if ("detail".equals(action)) {
            showPostDetail(request, response);
        } else if ("delete".equals(action)) {
            deletePost(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createPost(request, response);
        } else if ("update".equals(action)) {
            updatePost(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
        }
    }

    private void listPosts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("posts", postDao.getAllPosts());
        request.getRequestDispatcher("views/postList.jsp").forward(request, response);
    }

    private void showPostDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int postId = Integer.parseInt(request.getParameter("id"));
            Post post = postDao.getPostById(postId);
            if (post != null) {
                request.setAttribute("post", post);
                request.getRequestDispatcher("views/postDetail.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Post not found");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid post ID");
        }
    }

    private void createPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        User user = (User) request.getSession().getAttribute("user");

        if (title == null || title.isEmpty() || content == null || content.isEmpty()) {
            request.setAttribute("error", "Title and content cannot be empty");
            request.getRequestDispatcher("views/createPost.jsp").forward(request, response);
            return;
        }

        Post post = new Post(title, content, user.getId());
        postDao.savePost(post);

        response.sendRedirect("post?action=list");
    }

    private void updatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int postId = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            if (title == null || title.isEmpty() || content == null || content.isEmpty()) {
                request.setAttribute("error", "Title and content cannot be empty");
                request.getRequestDispatcher("views/updatePost.jsp").forward(request, response);
                return;
            }

            Post post = new Post(postId, title, content);
            postDao.updatePost(post);

            response.sendRedirect("post?action=list");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid post ID");
        }
    }

    private void deletePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int postId = Integer.parseInt(request.getParameter("id"));
            postDao.deletePost(postId);

            response.sendRedirect("post?action=list");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid post ID");
        }
    }
}
