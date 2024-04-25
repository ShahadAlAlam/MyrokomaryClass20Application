package org.saa.myrokomary_class20.repos;

import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.entity.BooksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BooksRepo{

    private List<Books> booksList = new ArrayList<>();

    public List<Books> getBooksList(){
        return this.booksList;
    }

    public Books addBooks(Books books){
      Long id;
      if(this.booksList.size()>0)
          id=this.booksList.stream().max((b,b1)->b.getBooksId() > b1.getBooksId() ? 1: -1).get().getBooksId()+1;
      else id = 1L;
      books.setBooksId(id);
      this.booksList.add(books);
      return books;
    }

    public void deleteBooks(Books books){
        this.booksList.remove(books);
    }

    public void updateBooks(Long id, Books books){
        this.booksList.forEach(b->{
            if(b.getBooksId()==id){
                b.setAuthor(books.getAuthor());
                b.setCountry(books.getCountry());
                b.setEdition(books.getEdition());
                b.setLanguage(books.getLanguage());
                b.setPublisher(books.getPublisher());
                b.setNumberOfPages(books.getNumberOfPages());
                b.setTitle(books.getTitle());
//                this.booksList.remove(b);
//                this.booksList.add(books);
            }
        });
    }
    public Books getBookById(Long booksId){
        return (Books) this.booksList.stream()
                .filter(b -> b.getBooksId()==booksId);
    }

    public void deleteBooksById(Long booksId){
        this.booksList.remove(getBookById(booksId));
    }
}
