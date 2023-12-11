package org.bookistore.com.services.impl;

import org.bookistore.com.dao.BookDao;
import org.bookistore.com.dao.UserBookDao;
import org.bookistore.com.dao.UserDao;
import org.bookistore.com.domain.models.Book;
import org.bookistore.com.domain.models.User;
import org.bookistore.com.services.LibraryService;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LibraryServiceImpl implements LibraryService {
    private final UserDao userDao;
    private final BookDao bookDao;
    private final UserBookDao userBookDao;
    public LibraryServiceImpl(UserDao userDao, BookDao bookDao, UserBookDao userBookDao) {
        this.userDao = userDao;
        this.bookDao = bookDao;
        this.userBookDao = userBookDao;
    }

    @Override
    public void addBook(Book book) throws SQLException {
        if (book!=null) {
            if (bookDao.addBook(book)>0) {
                System.out.println("Adición de libros exitosa! ");
            } else {
                System.out.println("Fallo la adición de libros");
            }
        }
    }

    @Override
    public void deleteBook(int bookId) throws SQLException {
        if(bookDao.deleteBook(bookId)>0) {
            System.out.println("Eliminación de libros exitosa!");
        } else {
            System.out.println("Fallo la adición de libros");
        }
    }

    @Override
    public void updateBook(Book book) throws SQLException {
        Book updatedBook = bookDao.getBookById(book.getBookId());
        if (!book.getName().isEmpty()) updatedBook.setName(book.getName());
        if (!book.getAuthor().isEmpty()) updatedBook.setAuthor(book.getAuthor());
        if (book.getGenre() != null) updatedBook.setGenre(book.getGenre());
        if (bookDao.updateBook(updatedBook)>0) {
            System.out.println("Actualización de libros exitosa");
        } else {
            System.out.println("Fallo la actualización de libros");
        }
    }

    @Override
    public void showAllBooks() throws SQLException {
        List<Book> books = bookDao.getAllBooks();
        for (Book book : books) {
            System.out.println("Libro = " + book.toString());
        }
    }

    @Override
    public Book findBook(String bookName) {
        List<Book> books;
        Book findBook = null;
        try {
            books = bookDao.getAllBooks();
            for (Book book : books) {
                if (!book.isDeleted()) {
                    if (book.getName().equals(bookName)) {
                        findBook = book;
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return findBook;
    }

    @Override
    public void addUser(User user) throws SQLException {
        if(user != null) {
            if (userDao.addUser(user)>0) {
                System.out.println("Adición de usuarios exitosa! ");
            } else {
                System.out.println("Falló la adición de usuarios! ");
            }
        } else {
            System.out.println("Usuario vacío");
        }
    }

    @Override
    public void deleteUser(int userId) throws SQLException {
        if(userDao.deleteUser(userId)>0) {
            System.out.println("Eliminación de usuarios exitosa!");
        } else {
            System.out.println("Fallo la eliminación de libros");
        }
    }

    @Override
    public void updateUser(int userId, String email) throws SQLException {
        User updatedUser = userDao.getUserById(userId);
        updatedUser.setEmail(email);
        if (userDao.updateUser(updatedUser)>0) {
            System.out.println("Actualización de libros exitosa");
        } else {
            System.out.println("Fallo la actualización de libros");
        }
    }

    @Override
    public void showAllUsers() throws SQLException {
        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            System.out.println("Usuario = " + user.toString());
        }
    }

    @Override
    public User findUser(String userName) {
        List<User> users;
        User findUser = null;
        try {
            users = userDao.getAllUsers();
            for (User user : users) {
                if (user.getUsername().equals(userName)) {
                    findUser = user;
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return findUser;
    }

    @Override
    public void loanBook(String bookName, String username) {
        User loanUsername;
        Book loanedBook;
        try {
            loanUsername = findUser(username);
            loanedBook = findBook(bookName);
            if (loanUsername != null) {
                if (!loanedBook.isLoaned()) {
                    if (bookDao.loanBook(loanedBook, loanUsername)) {
                        loanedBook.setLoaned(true);
                        bookDao.updateBook(loanedBook);
                        System.out.println("Prestamo exitoso");
                    } else {
                        System.out.println("Prestamo no fue exitoso");
                    };
                } else {
                    System.out.println("Error: El libro no existe o está prestado");
                }
            } else {
                System.out.println("Error: El usuario no existe o no está disponible");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void returnBook(String bookName, String username) {
        User loanUsername;
        Book returnedBook;
        try {
            loanUsername = findUser(username);
            returnedBook = findBook(bookName);
            if (loanUsername != null) {
                if (returnedBook.isLoaned()) {
                    if (bookDao.returnBook(returnedBook, loanUsername)) {
                        returnedBook.setLoaned(false);
                        bookDao.updateBook(returnedBook);
                        System.out.println("Devolución exitosa!");
                    } else {
                        System.out.println("Devolución no fue exitosa");
                    };
                } else {
                    System.out.println("Error: El libro no existe o ya fue devuelto");
                }
            } else {
                System.out.println("Error: El usuario no existe o no está disponible");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void showHistory() throws SQLException {
        Map<Integer, Integer> users_books = userBookDao.showHistory();
        User user = new User();
        Book book = new Book();
        for (Map.Entry<Integer, Integer>  entry : users_books.entrySet()) {
            user = userDao.getUserById(entry.getKey());
            book = bookDao.getBookById(entry.getValue());
            System.out.println("User = " + user.toString() +
                    ", Book = " + book.toString());
        }
    }
}
