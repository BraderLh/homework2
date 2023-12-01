package org.bookistore.com.dao.impl;

import org.bookistore.com.dao.UserDao;
import org.bookistore.com.domain.constants.Genre;
import org.bookistore.com.domain.models.Book;
import org.bookistore.com.domain.models.User;
import org.bookistore.com.utils.DbConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final String SQL_SELECT = "SELECT * FROM users";
    private static final String SQL_INSERT = "INSERT INTO users(username, email, userpassword, creationdate, updatedate, deletedate) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE users SET username=?, email=?, userpassword=?,updatedate=?  WHERE user_id=?";
    private static final String SQL_DELETE = "DELETE FROM users WHERE user_id=?";
    private static final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE user_id=?";

    @Override
    public List<User> getAllUsers() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        User user;
        List<User> users = new ArrayList<>();
        try {
            conn = DbConnect.getConnection();
            pstmt = conn.prepareStatement(SQL_SELECT);
            resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                int userId = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String userPassword = resultSet.getString("userpassword");
                Date creationDate = resultSet.getDate("creationdate");
                Date updateDate = resultSet.getDate("updatedate");
                Date deleteDate = resultSet.getDate("deletedate");

                user = new User();
                user.setUserId(userId);
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(userPassword);
                user.setCreationDate(creationDate);
                user.setUpdateDate(updateDate);
                user.setDeleteDate(deleteDate);

                users.add(user);
            }
        } finally{
            assert resultSet != null;
            DbConnect.close(resultSet);
            DbConnect.close(pstmt);
            DbConnect.close(conn);
        }

        return users;
    }

    @Override
    public int addUser(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        long millis = System.currentTimeMillis();
        try {
            conn = DbConnect.getConnection();
            System.out.println("Ejecutando query de insert: " + SQL_INSERT);
            pstmt = conn.prepareStatement(SQL_INSERT);

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setDate(4, new java.sql.Date(millis));
            pstmt.setNull(5, Types.DATE);
            pstmt.setNull(6, Types.DATE);
            rows = pstmt.executeUpdate();
            System.out.println("Registros insertados: " + rows);
        } finally{
            assert pstmt != null;
            DbConnect.close(pstmt);
            DbConnect.close(conn);
        }

        return rows;
    }

    @Override
    public int updateUser(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        long millis = System.currentTimeMillis();

        try {
            conn = DbConnect.getConnection();
            System.out.println("Ejecutando query: " + SQL_UPDATE);
            pstmt = conn.prepareStatement(SQL_UPDATE);

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setDate(4, new java.sql.Date(millis));
            pstmt.setInt(5, user.getUserId());

            rows = pstmt.executeUpdate();
            System.out.println("Registros actualizados: " + rows);

        } finally{
            assert pstmt != null;
            DbConnect.close(pstmt);
            DbConnect.close(conn);
        }

        return rows;
    }

    @Override
    public int deleteUser(int userId) throws SQLException {
        Connection conn = null;
        PreparedStatement ptmt = null;
        int rows = 0;

        try {
            conn = DbConnect.getConnection();
            System.out.println("Ejecutando query: " + SQL_DELETE);
            ptmt = conn.prepareStatement(SQL_DELETE);
            ptmt.setInt(1, userId);
            rows = ptmt.executeUpdate();
            System.out.println("Registros eliminados: " + rows);
        } finally{
            assert ptmt != null;
            DbConnect.close(ptmt);
            DbConnect.close(conn);
        }

        return rows;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = DbConnect.getConnection();
            System.out.println("Ejecutando query: " + SQL_GET_USER_BY_ID);
            pstmt = conn.prepareStatement(SQL_GET_USER_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String userPassword = rs.getString("userpassword");
                Date creationDate = rs.getDate("creationdate");
                Date updateDate = rs.getDate("updatedate");
                Date deleteDate = rs.getDate("deletedate");
                user = new User();
                user.setUserId(userId);
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(userPassword);
                user.setCreationDate(creationDate);
                user.setUpdateDate(updateDate);
                user.setDeleteDate(deleteDate);
            }
            System.out.println("Obteniendo libro con el id: " + id);
        } finally{
            assert rs != null;
            DbConnect.close(rs);
            DbConnect.close(pstmt);
            DbConnect.close(conn);
        }

        return user;
    }
}
