package com.wp.model;

public class Post {
    private int id;            // 게시글 ID
    private String title;      // 제목
    private String content;    // 내용
    private int authorId;      // 작성자 ID
    private String createdAt;  // 작성일시

    // 기본 생성자
    public Post() {
    }

    // ID 및 작성일시 없이 게시글을 생성하는 생성자
    public Post(String title, String content, int authorId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

    // ID, 제목 및 내용으로 게시글을 생성하는 생성자
    public Post(int id, String title, String content, int authorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }
    
 // ID, 제목, 내용을 받아 게시글을 생성하는 생성자
    public Post(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // 모든 필드를 포함하는 게시글을 생성하는 생성자
    public Post(int id, String title, String content, int authorId, String createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.createdAt = createdAt;
    }

    // Getter 및 Setter 메서드
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}