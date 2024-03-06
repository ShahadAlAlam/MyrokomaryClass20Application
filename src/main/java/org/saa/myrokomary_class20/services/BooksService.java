package org.saa.myrokomary_class20.services;

import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.repos.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class BooksService {
    @Autowired
    private BooksRepo booksRepo;

    public List<Books> getAllBooks(){
        return booksRepo.getBooksList();
    }

    public Books getBookById(Long id){
        return booksRepo.getBookById(id);
    }

    @PostMapping(value="/add-book")
    public void addBooks(Books books){
        booksRepo.addBooks(books);
    }

    public void updateBooks(Books books){
        booksRepo.updateBooks(books.getId(),books);
    }

    public void deleteBooks(Books books){
        booksRepo.deleteBooks(books);
    }
}
