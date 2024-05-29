package com.wp.movie;

import java.sql.Timestamp;

public class Post {
    private int id;
    private String title;
    private String author;
    private String content;
    private Timestamp createdAt;

    // 생성자 추가
    public Post(int id, String title, String author, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.createdAt = createdAt;
    }
    
    public Post(int id, String title, String content, String author, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
    }

    // 게시글 ID getter
    public int getId() {
        return id;
    }

    // 제목 getter
    public String getTitle() {
        return title;
    }

    // 내용 getter
    public String getContent() {
        return content;
    }

    // 작성자 getter
    public String getAuthor() {
        return author;
    }

    // 작성일 getter
    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
