package org.bookistore.com.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bookistore.com.domain.constants.Genre;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    private int bookId;
    private String name;
    private String author;
    private Genre genre;
    private boolean isDeleted;
    private boolean isLoaned;
    private Date creationDate;
    private Date updateDate;
    private Date deleteDate;

    public Book(String name, String author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }
}
