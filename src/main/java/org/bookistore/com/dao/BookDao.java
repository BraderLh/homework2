package org.bookistore.com.dao;

import org.bookistore.com.domain.models.Book;
import org.bookistore.com.domain.models.User;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    public List<Book> getAllBooks() throws SQLException;
    public int addBook(Book book) throws SQLException;
    public int updateBook(Book book) throws SQLException;
    public int deleteBook(int bookId) throws SQLException;
    public Book getBookById(int bookId) throws SQLException;
    public boolean loanBook(Book book, User user) throws SQLException;
    public boolean returnBook(Book book, User user) throws SQLException;
}
