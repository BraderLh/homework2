package org.bookistore.com.dao.impl;

import org.bookistore.com.dao.BookDao;
import org.bookistore.com.domain.constants.Genre;
import org.bookistore.com.domain.models.Book;
import org.bookistore.com.domain.models.User;
import org.bookistore.com.utils.DbConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookDaoImpl implements BookDao {

    private static final String SQL_SELECT = "SELECT * FROM books";
    private static final String SQL_INSERT = "INSERT INTO books(bname, author, genre, isdeleted, isloaned, creationdate, updatedate, deletedate) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE books SET bname=?, author=?, genre=?, isdeleted=?, isloaned=?, creationdate=?, updatedate=?, deletedate=? WHERE book_id=?";
    private static final String SQL_DELETE = "UPDATE books SET isdeleted=?, updatedate=?, deletedate=? WHERE book_id=?";
    private static final String SQL_GET_BOOK_BY_ID = "SELECT * FROM books WHERE book_id=?";
    private static final String SQL_LOAN_BOOK = "UPDATE books SET isloaned=? WHERE book_id=?";
    private static final String SQL_RETURN_BOOK = "UPDATE books SET isloaned=? WHERE book_id=?";
    private static final String SQL_INSERT_BOOK_USER = "INSERT INTO users_books(user_id, book_id) VALUES(?, ?)";
    private static final String SQL_DELETE_BOOK_USER = "DELETE FROM users_books WHERE book_id=?";

    public BookDaoImpl() {
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Book book;
        List<Book> books = new ArrayList<>();
        try {
            conn = DbConnect.getConnection();
            pstmt = conn.prepareStatement(SQL_SELECT);
            rs = pstmt.executeQuery();
            while(rs.next()){
                int bookId = rs.getInt("book_id");
                String name = rs.getString("bname");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                boolean isDeleted = rs.getBoolean("isdeleted");
                boolean isLoaned = rs.getBoolean("isloaned");
                Date creationDate = rs.getDate("creationdate");
                Date updateDate = rs.getDate("updatedate");
                Date deleteDate = rs.getDate("deletedate");

                book = new Book();
                book.setBookId(bookId);
                book.setName(name);
                book.setAuthor(author);
                book.setGenre(Genre.valueOf(genre));
                book.setDeleted(isDeleted);
                book.setLoaned(isLoaned);
                book.setCreationDate(creationDate);
                book.setUpdateDate(updateDate);
                book.setDeleteDate(deleteDate);
                assert false;
                books.add(book);
            }

        } finally{
            assert rs != null;
            DbConnect.close(rs);
            DbConnect.close(pstmt);
            DbConnect.close(conn);
        }

        return books;
    }

    @Override
    public int addBook(Book book) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        long millis = System.currentTimeMillis();
        try {
            conn = DbConnect.getConnection();
            System.out.println("Ejecutando query: " + SQL_INSERT);
            pstmt = conn.prepareStatement(SQL_INSERT);
            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getGenre().toString());
            pstmt.setBoolean(4, false);
            pstmt.setBoolean(5, false);
            pstmt.setDate(6, new java.sql.Date(millis));
            pstmt.setNull(7, Types.DATE);
            pstmt.setNull(8, Types.DATE);
            rows = pstmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } finally{
            assert pstmt != null;
            DbConnect.close(pstmt);
            DbConnect.close(conn);
        }
        return rows;
    }

    @Override
    public int updateBook(Book book) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows;
        long millis = System.currentTimeMillis();
        try {
            conn = DbConnect.getConnection();
            System.out.println("Ejecutando query: " + SQL_UPDATE);
            pstmt = conn.prepareStatement(SQL_UPDATE);
            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getGenre().toString());
            pstmt.setBoolean(4, book.isDeleted());
            pstmt.setBoolean(5, book.isLoaned());
            pstmt.setDate(6, (java.sql.Date) book.getCreationDate());
            pstmt.setDate(7, new java.sql.Date(millis));
            pstmt.setDate(8, (java.sql.Date) book.getDeleteDate());
            pstmt.setInt(9, book.getBookId());

            rows = pstmt.executeUpdate();
            System.out.println("Registros actualizados:" + rows);

        } finally{
            assert pstmt != null;
            DbConnect.close(pstmt);
            DbConnect.close(conn);
        }

        return rows;
    }

    @Override
    public int deleteBook(int bookId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        long millis = System.currentTimeMillis();
        try {
            conn = DbConnect.getConnection();
            System.out.println("Ejecutando query: " + SQL_DELETE);
            pstmt = conn.prepareStatement(SQL_DELETE);
            pstmt.setBoolean(1, true);
            pstmt.setNull(2, Types.DATE);
            pstmt.setDate(3, new java.sql.Date(millis));
            pstmt.setInt(4, bookId);
            rows = pstmt.executeUpdate();
            System.out.println("Registros eliminados: " + rows);

        } finally{
            assert pstmt != null;
            DbConnect.close(pstmt);
            DbConnect.close(conn);
        }

        return rows;
    }

    @Override
    public Book getBookById(int id) throws SQLException {
        Connection conn;
        PreparedStatement pstmt;
        ResultSet rs;
        Book book = null;
        try {
            conn = DbConnect.getConnection();
            System.out.println("Ejecutando query:" + SQL_GET_BOOK_BY_ID);
            pstmt = conn.prepareStatement(SQL_GET_BOOK_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int bookId = rs.getInt("book_id");
                String name = rs.getString("bname");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                boolean isDeleted = rs.getBoolean("isdeleted");
                boolean isLoaned = rs.getBoolean("isloaned");
                Date creationDate = rs.getDate("creationdate");
                Date updateDate = rs.getDate("updatedate");
                Date deleteDate = rs.getDate("deletedate");
                book = new Book();
                book.setBookId(bookId);
                book.setName(name);
                book.setAuthor(author);
                book.setGenre(Genre.valueOf(genre));
                book.setDeleted(isDeleted);
                book.setLoaned(isLoaned);
                book.setCreationDate(creationDate);
                book.setUpdateDate(updateDate);
                book.setDeleteDate(deleteDate);
            }
            System.out.println("Obteniendo libro con el id: " + id);
        } catch (SQLException ex) {
            System.out.println("Error: El libro no existe");
            ex.printStackTrace(System.out);
        }
        return book;
    }

    @Override
    public boolean loanBook(Book book, User user) throws SQLException {
        Connection conn;
        PreparedStatement pstmt;
        int rows;
        boolean isSuccess = false;
        try {
            conn = DbConnect.getConnection();
            System.out.println("Ejecutando query:" + SQL_LOAN_BOOK);
            pstmt = conn.prepareStatement(SQL_LOAN_BOOK);
            pstmt.setBoolean(1, book.isLoaned());
            pstmt.setInt(2, book.getBookId());
            try (PreparedStatement stmt = conn.prepareStatement(SQL_INSERT_BOOK_USER)) {
                    System.out.println("Ejecutando query:" + SQL_INSERT_BOOK_USER);
                    stmt.setInt(1, user.getUserId());
                    stmt.setInt(2, book.getBookId());
                    stmt.executeUpdate();
            }catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            isSuccess = true;
            System.out.println("Libro alquilado con el id:" + book.getBookId() + " al usuario con el id: " + user.getUserId());
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return isSuccess;
    }

    @Override
    public boolean returnBook(Book book, User user) throws SQLException {
        Connection conn;
        PreparedStatement pstmt;
        boolean isSuccess = false;
        try {
            conn = DbConnect.getConnection();
            System.out.println("Ejecutando query:" + SQL_RETURN_BOOK);
            pstmt = conn.prepareStatement(SQL_RETURN_BOOK);
            pstmt.setBoolean(1, book.isLoaned());
            pstmt.setInt(2, book.getBookId());
            try (PreparedStatement stmt = conn.prepareStatement(SQL_DELETE_BOOK_USER)) {
                System.out.println("Ejecutando query: " + SQL_DELETE_BOOK_USER);
                stmt.setInt(1, book.getBookId());
                stmt.executeUpdate();
            }catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            isSuccess = true;
            System.out.println("Libro retornado con el id: " + book.getBookId() + " del usuario con el id: " + user.getUserId());
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return isSuccess;
    }

}
