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
    public Books addBooks(Books books){
            return booksRepo.addBooks(books);
    }

    @Transactional
    public Books updateBooks(HashMap<String,Object> books){
            return booksRepo.updateBooks(Long.parseLong(books.get("id").toString()), new Books(books));
    }

    @Transactional
    public Books deleteBooks(Books books){
        booksRepo.deleteBooks(books);
        return books;
    }

    @Transactional
    public Long deleteBooksById(Long booksId){
        return booksRepo.deleteBooksById(booksId);
    }

}
