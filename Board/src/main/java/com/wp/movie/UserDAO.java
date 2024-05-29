package com.wp.movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    private final String jdbcURL = "jdbc:h2:tcp://localhost/~/test"; // H2 데이터베이스 연결 URL
    private final String jdbcUsername = "sa"; // H2 데이터베이스 사용자 이름
    private final String jdbcPassword = ""; // H2 데이터베이스 암호

    private static final String INSERT_USERS_SQL = "INSERT INTO users (username, password, email, phone, gender) VALUES (?, ?, ?, ?, ?);";

    public UserDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void register(User user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getGender());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
