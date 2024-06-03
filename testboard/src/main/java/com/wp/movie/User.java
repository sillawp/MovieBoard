package com.wp.movie;

public class User {
    private int id; // id �ʵ� �߰�
    private String username;
    private String password;
    private String email;
    private String phone;
    private String gender;
    private boolean isAdmin; // ����ڰ� ���������� ���θ� �����ϴ� ����

    // �⺻ ������
    public User() {}

    // ��� �ʵ带 ����ϴ� ������
    public User(int id, String username, String password, String email, String phone, String gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.isAdmin = isAdmin;
    }

    // Getter�� Setter �޼���
    public int getId() {
        return this.id;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
