package org.saa.myrokomary_class20.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.entity.BooksEntity;
import org.saa.myrokomary_class20.repos.BooksEntityRepo;
import org.saa.myrokomary_class20.utils.ApiResponse;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceDbImpl implements BooksService {
    private BooksEntityRepo booksEntityRepo;
    private BooksEntity booksEntity;

    BooksServiceDbImpl(BooksEntityRepo booksEntityRepo) {
        this.booksEntityRepo = booksEntityRepo;
    }

    ModelMapper mapper = new ModelMapper();

    public List<Books> getAllBooks() {
        List<Books> lb = new ArrayList<>();
        lb = mapper.map(booksEntityRepo.findAllByOrderByBooksIdAsc().stream().toList(), List.class);
        return lb;
    }

    public Books getBookById(Long booksId) {
//        return booksRepo.getBookById(id);
        Books b = new Books();
        mapper.map(booksEntityRepo.getReferenceById(booksId), b);
        return b;
    }

    @Transactional
    public ApiResponse addBooks(Books books) {
        try {
            Long id = booksEntityRepo.findMaxId();
            books.setBooksId(((id != null ? id : 1L)));
            return ApiResponse.build(HttpStatus.CREATED).body(booksEntityRepo.save(new BooksEntity(books))).details("Data Added Successfully");
        } catch (Exception ex) {
            return ApiResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage());
        }
    }

    @Transactional
    public ApiResponse updateBooks(HashMap<String, Object> books) {
//    public void updateBooks(Books books){
        Optional<BooksEntity> bookUpdOpt = booksEntityRepo.findById(Long.parseLong(books.get("booksId").toString()));
        BeanWrapper wrapper = new BeanWrapperImpl(bookUpdOpt.get());

        if (bookUpdOpt.isPresent()) {
        books.keySet().forEach(k->{
            try {
                wrapper.getPropertyDescriptor(k.toString()).getWriteMethod();
                wrapper.setPropertyValue(k.toString(), books.get(k.toString()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
//                    = books.get("title").toString();
        });

//            if (books.containsKey("title")) {
//                bookUpdOpt.get().title = books.get("title").toString();
//            }
//            if (books.containsKey("author")) {
//                bookUpdOpt.get().author = books.get("author").toString();
//            }
//            if (books.containsKey("publisher")) {
//                bookUpdOpt.get().publisher = books.get("publisher").toString();
//            }
//            if (books.containsKey("edition")) {
//                bookUpdOpt.get().edition = books.get("edition").toString();
//            }
//            if (books.containsKey("numberOfPages")) {
//                bookUpdOpt.get().numberOfPages = Long.parseLong(books.get("numberOfPages").toString());
//            }
//            if (books.containsKey("country")) {
//                bookUpdOpt.get().country = books.get("country").toString();
//            }
//            if (books.containsKey("language")) {
//                bookUpdOpt.get().language = books.get("language").toString();
//            }
            booksEntityRepo.save(bookUpdOpt.get());
            return ApiResponse.build(HttpStatus.OK).body(bookUpdOpt.get()).details("Data Saved Successfully");
        } else{
        return ApiResponse.build(HttpStatus.NOT_FOUND).body(books).message("message Book not found");
    }
}

    @Transactional
    public ApiResponse deleteBooks(Books books){
        try {
            booksEntityRepo.delete(new BooksEntity(books));
            return ApiResponse.build(HttpStatus.NO_CONTENT).message("Deleted Successfully");
        } catch (Exception ex){
            return ApiResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message("message Book not found");
        }
    }

    public ApiResponse deleteBooksById(Long booksId) {
        try {
            booksEntityRepo.deleteById(booksId);
            return ApiResponse.build(HttpStatus.OK).body("Deleted Successfully").message("Deleted Successfully").data("Deleted Successfully").details("Deleted Successfully");
        } catch (Exception ex){
            return ApiResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message("message Book not found").body("message Book not found").data("message Book not found").details("message Book not found");
        }
    }

}
