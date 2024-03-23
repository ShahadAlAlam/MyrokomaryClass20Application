package org.saa.myrokomary_class20.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.entity.BooksEntity;
import org.saa.myrokomary_class20.repos.BooksRepo;
import org.springframework.stereotype.Service;

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
    public void addBooks(BooksEntity books){

        booksRepo.addBooks(new Books(books));
    }

    @Transactional
    public void updateBooks(BooksEntity books){

        booksRepo.updateBooks(books.id,new Books( books));
    }

    @Transactional
    public void deleteBooks(BooksEntity books){
        booksRepo.deleteBooks(new Books( books));
    }

}
