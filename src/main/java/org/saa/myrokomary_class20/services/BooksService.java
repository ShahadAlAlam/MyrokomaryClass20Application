package org.saa.myrokomary_class20.services;

import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.entity.BooksEntity;
import org.saa.myrokomary_class20.utils.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface BooksService {
    public List<Books> getAllBooks();

    public Books getBookById(Long id);
//    @Transactional
//    @PostMapping(value="/add-book")
    public ApiResponse addBooks(Books books);

//    @Transactional
    public ApiResponse updateBooks(HashMap<String,Object> books);

//    @Transactional
    public ApiResponse deleteBooks(Books books);
    public ApiResponse deleteBooksById(Long id);

}
