package org.bookistore.com.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.*;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DbConnect {
    public static DataSource getDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        Properties properties = new Properties();
        try {
            InputStream inputStream = DbConnect.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(inputStream);
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            basicDataSource.setUrl(url);
            basicDataSource.setUsername(user);
            basicDataSource.setPassword(password);
            basicDataSource.setInitialSize(5);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return basicDataSource;
    }

    public static Connection getConnection() throws SQLException {
        return Objects.requireNonNull(getDataSource()).getConnection();
    }

    public static void close(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement preparedStatement) {
        try {
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
