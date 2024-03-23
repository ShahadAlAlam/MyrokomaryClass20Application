package org.saa.myrokomary_class20.controller;

import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.entity.BooksEntity;
import org.saa.myrokomary_class20.services.BooksServiceDbImpl;
import org.saa.myrokomary_class20.services.BooksService;
import org.saa.myrokomary_class20.services.BooksServiceInternalImpl;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksController {

//    @Autowired
    private BooksService booksService;

//    @Autowired
    private Environment env;
    BooksController(BooksServiceDbImpl booksServiceDb, BooksServiceInternalImpl booksServiceInternalImpl, Environment env){
        System.out.println("dbEnable="+env.getProperty("dbEnable"));
        if(env.getProperty("dbEnable").equals("true")) {
            this.booksService = booksServiceDb;
        }
        else {
            this.booksService = booksServiceInternalImpl;
        }
    }
    @GetMapping(value="/all-books-list")
    public List<Books> getAllBooks(){
        return booksService.getAllBooks();
    }

    @GetMapping(value="/get-book-by-id/{id}")
    public Books getBookById(@PathVariable(name="id") Long id){
        return booksService.getBookById(id);
    }

    @PostMapping(value="/add-book")
    public void addBooks(@RequestBody BooksEntity books){
        booksService.addBooks(books);
    }

    @PutMapping(value="/update-book")
    public void updateBooks(@RequestBody BooksEntity books){

        booksService.updateBooks(books);
    }

    @DeleteMapping(value="/delete-book")
    public void deleteBooks(@RequestBody BooksEntity books){
        booksService.deleteBooks(books);
    }


}
