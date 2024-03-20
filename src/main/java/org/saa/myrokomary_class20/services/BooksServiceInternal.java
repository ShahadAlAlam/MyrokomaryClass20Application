package org.saa.myrokomary_class20.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.entity.BooksEntity;
import org.saa.myrokomary_class20.repos.BooksEntityRepo;
import org.saa.myrokomary_class20.repos.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class BooksServiceInternal implements BooksServiceInterface {

    private BooksRepo booksRepo;

    BooksServiceInternal(BooksRepo booksRepo){
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
    public void updateBooks(Books books){

        booksRepo.updateBooks(books.getId(),books);
    }

    @Transactional
    public void deleteBooks(Books books){
        booksRepo.deleteBooks(books);
    }

}
