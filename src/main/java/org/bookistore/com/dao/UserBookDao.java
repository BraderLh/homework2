package org.bookistore.com.dao;

import org.bookistore.com.domain.models.Book;
import org.bookistore.com.domain.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserBookDao {
    public Map<Integer, Integer> showHistory() throws SQLException;
}
