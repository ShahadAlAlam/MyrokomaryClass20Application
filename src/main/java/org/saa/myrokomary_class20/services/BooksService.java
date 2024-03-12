package org.saa.myrokomary_class20.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.entity.BooksEntity;
import org.saa.myrokomary_class20.repos.BooksEntityRepo;
import org.saa.myrokomary_class20.repos.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class BooksService {
    @Autowired
    private BooksEntityRepo booksEntityRepo;

    @Autowired
    private BooksRepo booksRepo;

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
    @PostMapping(value="/add-book")
    public void addBooks(Books books){

//        booksRepo.addBooks(books);
        Long id=booksEntityRepo.findMaxId();
        books.setId((id!=null?id:1L));

        booksEntityRepo.save(new BooksEntity(books));
    }

    @Transactional
    public void updateBooks(Books books){

//        booksRepo.updateBooks(books.getId(),books);
        booksEntityRepo.save(new BooksEntity(books));
    }

    @Transactional
    public void deleteBooks(Books books){

//        booksRepo.deleteBooks(books);
        booksEntityRepo.delete(new BooksEntity(books));
    }

}
