package org.saa.myrokomary_class20.controller;

import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.repos.BooksRepo;
import org.saa.myrokomary_class20.services.BooksService;
import org.saa.myrokomary_class20.services.BooksServiceInterface;
import org.saa.myrokomary_class20.services.BooksServiceInternal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksController {

//    @Autowired
    private BooksServiceInterface booksServiceInterface;

//    @Autowired
    private Environment env;
    BooksController(BooksService booksService, BooksServiceInternal booksServiceInternal,Environment env){
        System.out.println("dbEnable="+env.getProperty("dbEnable"));
        if(env.getProperty("dbEnable").equals("true")) {
            this.booksServiceInterface = booksService;
        }
        else {
            this.booksServiceInterface = booksServiceInternal;
        }
    }
    @GetMapping(value="/all-books-list")
    public List<Books> getAllBooks(){
        return booksServiceInterface.getAllBooks();
    }

    @GetMapping(value="/get-book-by-id/{id}")
    public Books getBookById(@PathVariable(name="id") Long id){
        return booksServiceInterface.getBookById(id);
    }

    @PostMapping(value="/add-book")
    public void addBooks(@RequestBody Books books){
        booksServiceInterface.addBooks(books);
    }

    @PutMapping(value="/update-book")
    public void updateBooks(@RequestBody Books books){
        booksServiceInterface.updateBooks(books);
    }

    @DeleteMapping(value="/delete-book")
    public void deleteBooks(@RequestBody Books books){
        booksServiceInterface.deleteBooks(books);
    }


}
