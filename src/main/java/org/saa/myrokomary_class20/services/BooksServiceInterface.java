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
public interface BooksServiceInterface {
    public List<Books> getAllBooks();

    public Books getBookById(Long id);
    @Transactional
    @PostMapping(value="/add-book")
    public void addBooks(Books books);

    @Transactional
    public void updateBooks(Books books);

    @Transactional
    public void deleteBooks(Books books);

}
