package org.bookistore.com.dao;

import org.bookistore.com.domain.models.Book;
import org.bookistore.com.domain.models.User;

import java.util.List;

public interface UserBookDao {
    public void showHistory(List<User> users, List<Book> books);
}
