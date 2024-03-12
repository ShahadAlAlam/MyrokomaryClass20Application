package org.saa.myrokomary_class20.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.saa.myrokomary_class20.dto.Books;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table(name="books", schema="accounting")
public class BooksEntity {
    @Id
    @Column(updatable = false)
    public Long id;
    @Column
    public String title;
    @Column
    public String author;
    @Column
    public String publisher;
    @Column
    public String edition;
    @Column
    public Long numberOfPages;
    @Column
    public String country;
    @Column
    public String language;

    public BooksEntity(Books books) {
        this.id =  books.getId();
        this.title = books.getTitle();
        this.author = books.getAuthor();
        this.publisher = books.getPublisher();
        this.edition = books.getEdition();
        this.numberOfPages = books.getNumberOfPages();
        this.country = books.getCountry();
        this.language = books.getLanguage();
    }
    BooksEntity(){

    }
}
