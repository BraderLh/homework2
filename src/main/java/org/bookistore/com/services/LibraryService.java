package org.bookistore.com.services;

import org.bookistore.com.domain.models.Book;
import org.bookistore.com.domain.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface LibraryService {
    public void addBook(Book book) throws SQLException;
    public void deleteBook(int bookId) throws SQLException;
    public void updateBook(Book book) throws SQLException;
    public void showAllBooks() throws SQLException;
    public Book findBook(String bookName);

    public void addUser(User user) throws SQLException;
    public void deleteUser(int userId) throws SQLException;
    public void updateUser(int userId, String email) throws SQLException;
    public void showAllUsers() throws SQLException;
    public User findUser(String userName);
    public void loanBook(String bookName, String username);
    public void returnBook(String bookName, String username);
    public void showHistory() throws SQLException;
}
