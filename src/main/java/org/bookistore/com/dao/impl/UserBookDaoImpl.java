package org.bookistore.com.dao.impl;

import org.bookistore.com.dao.BookDao;
import org.bookistore.com.dao.UserBookDao;
import org.bookistore.com.dao.UserDao;
import org.bookistore.com.domain.models.Book;
import org.bookistore.com.domain.models.User;
import org.bookistore.com.utils.DbConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserBookDaoImpl implements UserBookDao {
    private UserDao userDao;
    private BookDao bookDao;

    private static final String SQL_SELECT = "SELECT * FROM users_books";

    @Override
    public Map<Integer, Integer> showHistory() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Book book;
        User user;
        Map<Integer, Integer> users_books = new HashMap<>();

        try {
            conn = DbConnect.getConnection();
            pstmt = conn.prepareStatement(SQL_SELECT);
            rs = pstmt.executeQuery();
            while(rs.next()){
                int userId = rs.getInt("user_id");
                int bookId = rs.getInt("book_id");
                users_books.put(userId, bookId);
            }

        } finally{
            assert rs != null;
            DbConnect.close(rs);
            DbConnect.close(pstmt);
            DbConnect.close(conn);
        }
        return users_books;
    }
}
