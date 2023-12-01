package org.bookistore.com;

import org.bookistore.com.dao.BookDao;
import org.bookistore.com.dao.UserDao;
import org.bookistore.com.dao.impl.BookDaoImpl;
import org.bookistore.com.dao.impl.UserDaoImpl;
import org.bookistore.com.domain.constants.Genre;
import org.bookistore.com.domain.models.Book;
import org.bookistore.com.domain.models.User;
import org.bookistore.com.services.LibraryService;
import org.bookistore.com.services.impl.LibraryServiceImpl;
import org.bookistore.com.utils.DbConnect;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        BookDao bookDao = new BookDaoImpl();
        LibraryService libraryService = new LibraryServiceImpl(userDao, bookDao);

        /*User user1 = new User("usertest1", "usertest1@gmail.com", "qwer123");
        User user2 = new User("usertest2", "usertest2@gmail.com", "qwer456");
        User user3 = new User("usertest3", "usertest3@gmail.com", "qwer678");
        User user4 = new User("usertest4", "usertest4@gmail.com", "qwer910");
        User user5 = new User("usertest5", "usertest5@gmail.com", "qwer110");
        User user6 = new User("usertest6", "usertest6@gmail.com", "qwer111");
        User user7 = new User("usertest7", "usertest7@gmail.com", "qwer112");*/
        //libraryService.addUser(user2);
        //libraryService.deleteUser(12);
        //System.out.println("Usuario buscado: " + libraryService.findUser("dhuby4").toString());
        //libraryService.updateUser(5, "nuevoemail1@gmail.com");
        //System.out.println("Usuario buscado: " + libraryService.findUser("dhuby4").toString());
        //libraryService.showAllUsers();
        /*Book book1 = new Book("Book1", "Author1", Genre.Action);
        Book book2 = new Book("Book2", "Author2", Genre.Fantasy);
        Book book3 = new Book("Book3", "Author3", Genre.Fiction);
        Book book4 = new Book("Book4", "Author4", Genre.Horror);
        Book book5 = new Book("Book5", "Author5", Genre.Mystery);
        Book book6 = new Book("Book6", "Author6", Genre.Realist);
        Book book7 = new Book("Book7", "Author7", Genre.Romance);
        Book book8 = new Book("Book8", "Author8", Genre.Horror);
        Book book9 = new Book("Book9", "Author9", Genre.Historical);
        Book book10 = new Book("Book10", "Author10", Genre.ScienceFiction);
        List<Book> dataBook = new ArrayList<>();
        dataBook.add(book1);
        dataBook.add(book2);
        dataBook.add(book3);
        dataBook.add(book4);
        dataBook.add(book5);
        dataBook.add(book6);
        dataBook.add(book7);
        dataBook.add(book8);
        dataBook.add(book9);
        dataBook.add(book10);
        for (Book book : dataBook) {
            libraryService.addBook(book);
        }
        */
        //libraryService.deleteBook(10);
        Book updateBook = new Book();
        updateBook.setBookId(7);
        updateBook.setName("Libro numero 7");
        updateBook.setAuthor("Autor del libro 7");
        libraryService.updateBook(updateBook);
        libraryService.showAllBooks();
    }
}