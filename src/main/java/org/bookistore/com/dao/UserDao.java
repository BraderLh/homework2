package org.bookistore.com.dao;

import org.bookistore.com.domain.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public List<User> getAllUsers() throws SQLException;
    public int addUser(User user) throws SQLException;
    public int updateUser(User user) throws SQLException;
    public int deleteUser(int userId) throws SQLException;
    User getUserById(int userId) throws SQLException;
}
