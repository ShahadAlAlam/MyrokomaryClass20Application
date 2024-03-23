package org.saa.myrokomary_class20.dto;

import org.saa.myrokomary_class20.entity.BooksEntity;

import java.util.HashMap;
import java.util.Optional;

public class Books  implements Comparable {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String edition;
    private Long numberOfPages;
    private String country;
    private String language;
    public Books(){

    }
    public Books(Long id, String title, String author, String publisher, String edition, Long numberOfPages, String country, String language) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.edition = edition;
        this.numberOfPages = numberOfPages;
        this.country = country;
        this.language = language;
    }

    public Books(String title, String author, String publisher, String edition, Long numberOfPages, String country, String language) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.edition = edition;
        this.numberOfPages = numberOfPages;
        this.country = country;
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Books{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", edition='" + edition + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", country='" + country + '\'' +
                ", language='" + language + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Books b = (Books) o;
        if(this.id>b.id)
        return 1;
        else if(this.id<b.id){
            return -1;
        } else return 0;
    }

    public Books (BooksEntity books) {
        this.id =  books.id;
        this.title = books.title;
        this.author = books.author;
        this.publisher = books.publisher;
        this.edition = books.edition;
        this.numberOfPages = books.numberOfPages;
        this.country = books.country;
        this.language = books.language;
    }

    public Books (HashMap<String,Object> books) {
        this.id =Long.parseLong( books.get("id").toString());
        if( books.containsKey("title")) {
            this.title = books.get("title").toString();
        }
        if( books.containsKey("author")) {
            this.author = books.get("author").toString();
        }
        if( books.containsKey("publisher")) {
            this.publisher = books.get("publisher").toString();
        }
        if( books.containsKey("edition")) {
            this.edition = books.get("edition").toString();
        }
        if( books.containsKey("numberOfPages")) {
            this.numberOfPages =Long.parseLong( books.get("numberOfPages").toString());
        }
        if( books.containsKey("country")) {
            this.country = books.get("country").toString();
        }
        if( books.containsKey("language")) {
            this.language = books.get("language").toString();
        }
    }
}
