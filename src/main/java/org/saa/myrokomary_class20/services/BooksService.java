package org.saa.myrokomary_class20.services;

import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.entity.BooksEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BooksService {
    public List<Books> getAllBooks();

    public Books getBookById(Long id);
//    @Transactional
//    @PostMapping(value="/add-book")
    public void addBooks(BooksEntity books);

//    @Transactional
    public void updateBooks(BooksEntity books);

//    @Transactional
    public void deleteBooks(BooksEntity books);

}
