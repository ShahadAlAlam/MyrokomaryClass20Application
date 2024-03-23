package org.saa.myrokomary_class20.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.entity.BooksEntity;
import org.saa.myrokomary_class20.repos.BooksEntityRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public void addBooks(BooksEntity books){

//        booksRepo.addBooks(books);
        Long id=booksEntityRepo.findMaxId();
        books.id = ((id!=null?id:1L));

        booksEntityRepo.save(books);
    }

    @Transactional
    public void updateBooks(BooksEntity books){
        Optional<BooksEntity> bookUpdOpt = booksEntityRepo.findById(books.id);
        if(bookUpdOpt.isPresent()){
            bookUpdOpt.get().title = books.title;
        }
        booksEntityRepo.save(bookUpdOpt.get());
    }

    @Transactional
    public void deleteBooks(BooksEntity books){

//        booksRepo.deleteBooks(books);
        booksEntityRepo.delete(books);
    }

}
