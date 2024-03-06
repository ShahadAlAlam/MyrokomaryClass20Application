package org.saa.myrokomary_class20.dto;

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
}
