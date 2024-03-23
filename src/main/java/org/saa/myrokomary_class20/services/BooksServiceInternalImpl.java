package org.saa.myrokomary_class20.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.entity.BooksEntity;
import org.saa.myrokomary_class20.repos.BooksRepo;
import org.saa.myrokomary_class20.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BooksServiceInternalImpl implements BooksService {

    private BooksRepo booksRepo;

    BooksServiceInternalImpl(BooksRepo booksRepo){
        this.booksRepo=booksRepo;
    }
    ModelMapper mapper =  new ModelMapper();
    public List<Books> getAllBooks(){
        return booksRepo.getBooksList();
    }

    public Books getBookById(Long id){
        return booksRepo.getBookById(id);
    }

    @Transactional
    public void addBooks(Books books){

        booksRepo.addBooks(books);
    }

    @Transactional
    public ApiResponse updateBooks(HashMap<String,Object> books){
        try {
            booksRepo.updateBooks(Long.parseLong(books.get("id").toString()), new Books(books));
            return ApiResponse.build(HttpStatus.OK).body(books);

        } catch (Exception ex){
            return ApiResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Transactional
    public void deleteBooks(Books books){
        booksRepo.deleteBooks(books);
    }

}
