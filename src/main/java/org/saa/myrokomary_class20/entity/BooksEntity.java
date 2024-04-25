package org.saa.myrokomary_class20.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.saa.myrokomary_class20.config.AppProperties;
import org.saa.myrokomary_class20.dto.Books;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

@Entity
@DynamicUpdate(value = true)
@Table(name="books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BooksEntity implements Serializable {

    @Id
    @Column(updatable = false,name = "books_id")
    public Long booksId;
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
        this.booksId =  books.getBooksId();
        this.title = books.getTitle();
        this.author = books.getAuthor();
        this.publisher = books.getPublisher();
        this.edition = books.getEdition();
        this.numberOfPages = books.getNumberOfPages();
        this.country = books.getCountry();
        this.language = books.getLanguage();
    }

    @OneToMany(mappedBy = "books", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderItemsEntity> orderItemsEntity;

//    BooksEntity(){
//
//    }

//@ManyToMany(mappedBy = "orderItems")
//List<OrderEntity> orderItems;



// no need for getter setter because used Lombok Getter Setter
//    if we do not use Lombok Getter and Setter then below dynamic value set will not work
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

// no need for getter setter because used Lombok Getter Setter
//    if we do not use Lombok Getter and Setter then below dynamic value set will not work
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getPublisher() {
//        return publisher;
//    }
//
//    public void setPublisher(String publisher) {
//        this.publisher = publisher;
//    }
//
//    public String getEdition() {
//        return edition;
//    }
//
//    public void setEdition(String edition) {
//        this.edition = edition;
//    }
//
//    public Long getNumberOfPages() {
//        return numberOfPages;
//    }
//
//    public void setNumberOfPages(Long numberOfPages) {
//        this.numberOfPages = numberOfPages;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getLanguage() {
//        return language;
//    }
//
//    public void setLanguage(String language) {
//        this.language = language;
//    }
}
