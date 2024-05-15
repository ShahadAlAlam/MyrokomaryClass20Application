package org.saa.myrokomary_class20.controller;

import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.entity.BooksEntity;
import org.saa.myrokomary_class20.projections.BooksEntityProjection;
import org.saa.myrokomary_class20.services.BooksServiceDbImpl;
import org.saa.myrokomary_class20.services.BooksService;
import org.saa.myrokomary_class20.services.BooksServiceInternalImpl;
import org.saa.myrokomary_class20.utils.ApiResponse;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController //it only work with jSON so thymeleaf will not work
@RequestMapping("/books")
//@Controller // for thymeleaf
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
//    @GetMapping(value="/basicauth")
//    public ApiResponse basicAuth(){
//
//        return ApiResponse.build(HttpStatus.OK).body("successfull");
//    }
//    @GetMapping(value="/")
    @CrossOrigin
    @GetMapping(value="/all-books-list")
    public ApiResponse getAllBooks(@RequestParam(name="pageNumber",defaultValue="1") int pageNumber,@RequestParam(name="pageSize",defaultValue="null") int pageSize){
//        return booksService.getAllBooks(pageNumber,pageSize);
        try {
            List<Books> books = booksService.getAllBooks(pageNumber,pageSize);
            CollectionModel<Books> entityModel = CollectionModel.of(books);
            WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getAllBooks(1,1));
            return ApiResponse.build(HttpStatus.OK)
                    .body(books,linkBuilder,"/books")
                    .message("Data Found");
        } catch (Exception ex){
            return ApiResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).error(ex.getMessage());
        }
    }
    @CrossOrigin
    @GetMapping(value="/all-books-list-proj")
    public List<BooksEntityProjection> getAllBooksProj(@RequestParam(name="pageNumber",defaultValue="1") int pageNumber, @RequestParam(name="pageSize",defaultValue="null") int pageSize){
        return booksService.getAllBooksProj(pageNumber,pageSize);
    }

    @GetMapping(value="/get-book-by-id/{id}")
    public ApiResponse getBookById(@PathVariable(name="id") Long id){
        try {
            Books books = booksService.getBookById(id);
            WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getBookById(id));
            return ApiResponse.build(HttpStatus.OK)
                    .body(books,linkBuilder,"/all-books-list")
                    .message("Data Found");

        } catch (Exception ex){
            return ApiResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).data(id).error(ex.getMessage());
        }
    }

    @PostMapping(value="/add-book")
    public ApiResponse addBooks(@RequestBody Books books){
        try {
            WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).addBooks(books));
            return ApiResponse.build(HttpStatus.OK).body(booksService.addBooks(books), linkBuilder, "/all-books-list");
        }  catch (Exception ex){
            return ApiResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).data(books).error(ex.getMessage());
        }
    }

    @PutMapping(value="/update-book")
    public ApiResponse updateBooks(@RequestBody HashMap<String,Object> books){
        try {
            WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).updateBooks(books));
            return ApiResponse.build(HttpStatus.OK).body(booksService.updateBooks(books),linkBuilder,"/all-books-list");
        }  catch (Exception ex){
            return ApiResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).data(books).error(ex.getMessage());
        }
    }

    @DeleteMapping(value="/delete-book")
    public ApiResponse deleteBooks(@RequestBody Books books){
        try {
            WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).deleteBooks(books));
            return ApiResponse.build(HttpStatus.OK).body(booksService.deleteBooks(books),linkBuilder,"/all-books-list");
        }  catch (Exception ex){
            return ApiResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).data(books).error(ex.getMessage());
        }
    }
    @DeleteMapping(value="/delete-book-by-id/{booksId}")
    public ApiResponse deleteBooksById(@PathVariable(name="booksId") Long booksId){
        try {
            WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).deleteBooksById(booksId));
            return ApiResponse.build(HttpStatus.MOVED_PERMANENTLY).body(booksService.deleteBooksById(booksId),linkBuilder,"/all-books-list").message("Deleted Succfully");
        }  catch (Exception ex){
            return ApiResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).data(booksId).error(ex.getMessage());
        }
    }
//    @CrossOrigin
//    @GetMapping(value="/all-books-list-tl")
//    public String getAllBooksTl(Model model){
////        model.addAttribute("books", "Hello World!");
//        List<Books> books = booksService.getAllBooks();
//        model.addAttribute("books", books);
//        return "listbooks";
//    }


}
