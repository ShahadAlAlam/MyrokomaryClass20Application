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
import java.lang.reflect.Field;

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

    public <T extends Field> void setValue(String field, Object value)
    {
        try {
            T f= (T) this.getClass().getField(field);
            f.set(this,(T)value);
//           if( this.getClass().getField(field).getType().getTypeName().equals("java.lang.String")){
//               this.getClass().getField(field).set(this.getClass().getField(field),
//                       value.toString());
//           } else if (this.getClass().getField(field).getType().getTypeName().equals("Long")){
//
//               this.getClass().getField(field).set(this.getClass().getField(field).get(field.toString()),
//                      Long.parseLong( value.toString()));
//           }
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Long getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Long numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
