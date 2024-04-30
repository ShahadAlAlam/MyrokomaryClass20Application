package org.saa.myrokomary_class20.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.entity.BooksEntity;
import org.saa.myrokomary_class20.projections.BooksEntityProjection;
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
    public List<Books> getAllBooks(int pageNumber,int pageSize){
        return booksRepo.getBooksList();
    }
    public List<BooksEntityProjection> getAllBooksProj(int pageNumber, int pageSize){
        return null;
    };

    public Books getBookById(Long id){
        return booksRepo.getBookById(id);
    }

    @Transactional
    public ApiResponse addBooks(Books books){
        try {
            return ApiResponse.build(HttpStatus.CREATED).body(booksRepo.addBooks(books));
        } catch (Exception ex){
            return ApiResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage());
        }

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
    public ApiResponse deleteBooks(Books books){

        try {
            booksRepo.deleteBooks(books);
            return ApiResponse.build(HttpStatus.NO_CONTENT).message("Deleted Successfully");
        } catch (Exception ex){
            return ApiResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message("message Book not found");
        }
    }

    @Transactional
    public ApiResponse deleteBooksById(Long booksId){

        try {
            booksRepo.deleteBooksById(booksId);
            return ApiResponse.build(HttpStatus.NO_CONTENT).message("Deleted Successfully");
        } catch (Exception ex){
            return ApiResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message("message Book not found");
        }
    }

}
