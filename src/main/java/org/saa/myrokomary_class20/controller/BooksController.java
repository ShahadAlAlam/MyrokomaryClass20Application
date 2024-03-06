package org.saa.myrokomary_class20.controller;

import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.repos.BooksRepo;
import org.saa.myrokomary_class20.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksController {

    @Autowired
    private BooksService booksService;
    @GetMapping(value="/all-books-list")
    public List<Books> getAllBooks(){
        return booksService.getAllBooks();
    }

    @GetMapping(value="/get-book-by-id/{id}")
    public Books getBookById(@PathVariable(name="id") Long id){
        return booksService.getBookById(id);
    }

    @PostMapping(value="/add-book")
    public void addBooks(@RequestBody Books books){
        booksService.addBooks(books);
    }

    @PutMapping(value="/update-book")
    public void updateBooks(@RequestBody Books books){
        booksService.updateBooks(books);
    }

    @DeleteMapping(value="/delete-book")
    public void deleteBooks(@RequestBody Books books){
        booksService.deleteBooks(books);
    }


}
