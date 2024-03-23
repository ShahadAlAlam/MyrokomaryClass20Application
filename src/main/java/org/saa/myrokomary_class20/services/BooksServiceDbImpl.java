package org.saa.myrokomary_class20.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.entity.BooksEntity;
import org.saa.myrokomary_class20.repos.BooksEntityRepo;
import org.saa.myrokomary_class20.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceDbImpl implements BooksService {
    private BooksEntityRepo booksEntityRepo;

    BooksServiceDbImpl(BooksEntityRepo booksEntityRepo){
        this.booksEntityRepo = booksEntityRepo;
    }

    ModelMapper mapper =  new ModelMapper();
    public List<Books> getAllBooks(){
//        return booksRepo.getBooksList();
        List<Books> lb = new ArrayList<>();
        lb=mapper.map ( booksEntityRepo.findAll().stream().toList(),List.class);
        return lb;
    }

    public Books getBookById(Long id){
//        return booksRepo.getBookById(id);
        Books b = new Books();
        mapper.map( booksEntityRepo.getReferenceById(id),b);
        return b;
    }

    @Transactional
    public void addBooks(Books books){

//        booksRepo.addBooks(books);
        Long id=booksEntityRepo.findMaxId();
        books.setId(((id!=null?id:1L)));
        booksEntityRepo.save(new BooksEntity(books));
    }

    @Transactional
    public ApiResponse updateBooks(HashMap<String,Object> books){
//    public void updateBooks(Books books){

        Optional<BooksEntity> bookUpdOpt = booksEntityRepo.findById(Long.parseLong(books.get("id").toString()));
        if(bookUpdOpt.isPresent()){
           if( books.containsKey("title")) {
               bookUpdOpt.get().title = books.get("title").toString();
           }
           if( books.containsKey("author")) {
                bookUpdOpt.get().author = books.get("author").toString();
           }
           if( books.containsKey("publisher")) {
                bookUpdOpt.get().publisher = books.get("publisher").toString();
           }
           if( books.containsKey("edition")) {
                bookUpdOpt.get().edition = books.get("edition").toString();
           }
           if( books.containsKey("numberOfPages")) {
                bookUpdOpt.get().numberOfPages =Long.parseLong( books.get("numberOfPages").toString());
           }
           if( books.containsKey("country")) {
                bookUpdOpt.get().country = books.get("country").toString();
           }
           if( books.containsKey("language")) {
                bookUpdOpt.get().language = books.get("language").toString();
           }
           booksEntityRepo.save(bookUpdOpt.get());
        } else {
            return ApiResponse.build(HttpStatus.NOT_FOUND).body(books).message("message Book not found");
        }
        return ApiResponse.build(HttpStatus.OK).body(books)  ;
    }

    @Transactional
    public void deleteBooks(Books books){

//        booksRepo.deleteBooks(books);
        booksEntityRepo.delete(new BooksEntity(books));
    }

}
