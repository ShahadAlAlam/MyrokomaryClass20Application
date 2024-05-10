package org.saa.myrokomary_class20.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.saa.myrokomary_class20.config.security.basic.BasicAuthConfig;
import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.entity.TestDtlEntity;
import org.saa.myrokomary_class20.entity.TestEntity;
import org.saa.myrokomary_class20.repos.TestDtlEntityRepo;
import org.saa.myrokomary_class20.repos.TestEntityRepo;
import org.saa.myrokomary_class20.utils.ApiResponse;
import org.saa.myrokomary_class20.utils.DynamicSave;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TestServiceDbImpl {
    private TestEntityRepo testEntityRepo;
    private TestDtlEntityRepo testDtlEntityRepo;

    @Autowired
    private BasicAuthConfig basicAuthConfig;

    @Autowired
    private DynamicSave dynamicSave;

    TestServiceDbImpl(TestEntityRepo testEntityRepo, TestDtlEntityRepo testDtlEntityRepo) {
        this.testEntityRepo = testEntityRepo;
        this.testDtlEntityRepo = testDtlEntityRepo;
    }

    ModelMapper mapper = new ModelMapper();

    public List<Books> getAllBooks() {
        List<Books> lb = new ArrayList<>();
        lb = mapper.map(testEntityRepo.findAll().stream().toList(), List.class);
        return lb;
    }

    //
//    public List<BooksEntityProjection> getAllBooksProj(int pageNumber, int pageSize){
//        Pageable pageable = PageRequest.of(pageNumber,pageSize);
//        return booksEntityRepo.findAllSpecific(pageable);
//    }
//    public List<Books> getAllBooks(int pageNumber,int pageSize) {
//        Pageable pageable = PageRequest.of(pageNumber,pageSize);
//        List<Books> lb = new ArrayList<>();
//        lb = mapper.map(booksEntityRepo.findAllByOrderByBooksIdAsc(pageable).stream().toList(), List.class);
//        return lb;
//    }
//
//    public Books getBookById(Long booksId) {
////        return booksRepo.getBookById(id);
//        Books b = new Books();
//        mapper.map(booksEntityRepo.getReferenceById(booksId), b);
//        return b;
//    }
//
    @Transactional
    public ApiResponse addTestData(TestEntity testEntity) {
        testEntity.setCidId(1L);
        Long creId = basicAuthConfig.getUserDetailsService().getAccountEntityData().getAccountId();
        testEntity.setCreatedBy(creId);
        testEntity.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
        try {
            return ApiResponse.build(HttpStatus.CREATED).body(testEntityRepo.save(testEntity)).details("Data Added Successfully");
        } catch (Exception ex) {
            return ApiResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage());
        }
    }

    @Transactional
    public ApiResponse updateTestData(HashMap<String, Object> books) {
        Optional<TestEntity> testEntityOptional = testEntityRepo.findById(Long.parseLong(books.get("testId").toString()));
        BeanWrapper wrapper = new BeanWrapperImpl(testEntityOptional.get());

        if (testEntityOptional.isPresent()) {
            books.keySet().forEach(k -> {
                try {
                    if (wrapper.isWritableProperty(k.toString())) {
                        wrapper.setPropertyValue(k.toString(), books.get(k.toString()));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
            Long modId = basicAuthConfig.getUserDetailsService().getAccountEntityData().getAccountId();
            testEntityOptional.get().setModifiedBy(modId);

            testEntityOptional.get().setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            testEntityRepo.save(testEntityOptional.get());
            return ApiResponse.build(HttpStatus.OK).body(testEntityOptional.get()).details("Data Saved Successfully");
        } else {
            return ApiResponse.build(HttpStatus.NOT_FOUND).body(books).message("message Book not found");
        }
    }

    @Transactional
    public ApiResponse updateTestDataWithSpecial(HashMap<String, Object> testData) {
        TestEntity te = new TestEntity();
        te = dynamicSave.update(new TestEntity(), testEntityRepo, Long.parseLong( testData.get("testId").toString()), testData);
        List<TestDtlEntity> tds = dynamicSave.updateDetails(new TestDtlEntity(),testDtlEntityRepo,
                te, (List<HashMap<String,Object>>)testData.get("testDtlEntityList"));
        te.setTestDtlEntityList(tds);
        return ApiResponse.build(HttpStatus.OK).body(te).details("Data Saved Successfully");
    }
//
//    @Transactional
//    public ApiResponse deleteBooks(Books books){
//        try {
//            booksEntityRepo.delete(new BooksEntity(books));
//            return ApiResponse.build(HttpStatus.NO_CONTENT).message("Deleted Successfully");
//        } catch (Exception ex){
//            return ApiResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message("message Book not found");
//        }
//    }
//
//    public ApiResponse deleteBooksById(Long booksId) {
//        try {
//            booksEntityRepo.deleteById(booksId);
//            return ApiResponse.build(HttpStatus.OK).body("Deleted Successfully").message("Deleted Successfully").data("Deleted Successfully").details("Deleted Successfully");
//        } catch (Exception ex){
//            return ApiResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message("message Book not found").body("message Book not found").data("message Book not found").details("message Book not found");
//        }
//    }

}
