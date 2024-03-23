package org.saa.myrokomary_class20.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;
import org.saa.myrokomary_class20.dto.Books;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@Entity
@DynamicUpdate(value = true)
@Table(name="books", schema="accounting")
public class BooksEntity implements Serializable {
    @Id
    @Column(updatable = false)
    public Long id;
    @Column(name="title")
    public String title;
    @Column(name="author")
    public String author;
    @Column(name="publisher")
    public String publisher;
    @Column(name="edition")
    public String edition;
    @Column(name="numberOfPages")
    public Long numberOfPages;
    @Column(name="country")
    public String country;
    @Column(name="language")
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
