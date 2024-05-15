package org.saa.myrokomary_class20.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.entity.BooksEntity;
import org.saa.myrokomary_class20.projections.BooksEntityProjection;
import org.saa.myrokomary_class20.repos.BooksEntityRepo;
import org.saa.myrokomary_class20.utils.ApiResponse;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyAccessor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<BooksEntityProjection> getAllBooksProj(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return booksEntityRepo.findAllSpecific(pageable);
    }

    public List<Books> getAllBooks(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Books> lb = new ArrayList<>();
        lb = mapper.map(booksEntityRepo.findAllByOrderByBooksIdAsc(pageable).stream().toList(), List.class);
        return lb;
    }

    public Books getBookById(Long booksId) {
//        return booksRepo.getBookById(id);
        Books b = new Books();
        mapper.map(booksEntityRepo.getReferenceById(booksId), b);
        return b;
    }

    @Transactional
    public Books addBooks(Books books) {
        Long id = booksEntityRepo.findMaxId();
        books.setBooksId(((id != null ? id : 1L)));
        return new Books( booksEntityRepo.save(new BooksEntity(books)));
    }

    @Transactional
    public Books updateBooks(HashMap<String, Object> books) {
//    public void updateBooks(Books books){
        Optional<BooksEntity> bookUpdOpt = booksEntityRepo.findById(Long.parseLong(books.get("booksId").toString()));
        BeanWrapper wrapper = new BeanWrapperImpl(bookUpdOpt.get());

        books.keySet().forEach(k -> {
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

        return new Books( booksEntityRepo.save(bookUpdOpt.get()));
    }

    @Transactional
    public Books deleteBooks(Books books) {
        booksEntityRepo.delete(new BooksEntity(books));
        return books;
    }

    public Long deleteBooksById(Long booksId) {
        booksEntityRepo.deleteById(booksId);
        return  booksId;
    }

}
