package org.saa.myrokomary_class20.services;

import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.entity.BooksEntity;
import org.saa.myrokomary_class20.projections.BooksEntityProjection;
import org.saa.myrokomary_class20.utils.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface BooksService {
    public List<Books> getAllBooks();
    public List<Books> getAllBooks(int pageNumber,int pageSize);

    public List<BooksEntityProjection> getAllBooksProj(int pageNumber, int pageSize);
    public Books getBookById(Long id);
//    @Transactional
//    @PostMapping(value="/add-book")
    public Books addBooks(Books books);

//    @Transactional
    public Books updateBooks(HashMap<String,Object> books);

//    @Transactional
    public Books deleteBooks(Books books);
    public Long deleteBooksById(Long booksId);

}
