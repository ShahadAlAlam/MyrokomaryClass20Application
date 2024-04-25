package org.saa.myrokomary_class20.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.saa.myrokomary_class20.dto.OrderDto;
import org.saa.myrokomary_class20.entity.OrderEntity;
import org.saa.myrokomary_class20.entity.OrderItemsEntity;
import org.saa.myrokomary_class20.repos.OrderEntityRepo;
import org.saa.myrokomary_class20.repos.OrderItemsEntityRepo;
import org.saa.myrokomary_class20.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    private OrderEntityRepo orderEntityRepo;

    @Autowired
    private OrderItemsEntityRepo orderItemsEntityRepo;
    private OrderEntity orderEntity;

    OrderService(OrderEntityRepo orderEntityRepo) {
        this.orderEntityRepo = orderEntityRepo;
    }

    ModelMapper mapper = new ModelMapper();
//
//    public Books getBookById(Long booksId) {
////        return booksRepo.getBookById(id);
//        Books b = new Books();
//        mapper.map(booksEntityRepo.getReferenceById(booksId), b);
//        return b;
//    }


    public ApiResponse getAllOrders(Long accountId) {
       Optional<List<OrderEntity>> data = Optional.ofNullable(orderEntityRepo.findAllByAccountAccountId(accountId));
       if(data.isPresent()){
           List<OrderDto> orderDtos = new ArrayList<>();
           List<OrderEntity> orders = data.get();
           orders.stream().forEach(e->{
               OrderDto od = new OrderDto();
               od.setOrder(e);
               od.setOrderItems(e.getOrderItems());
               orderDtos.add(od);
           });
           return ApiResponse.build(HttpStatus.OK).data(orderDtos).details("data found");
       }
       else
        return ApiResponse.build(HttpStatus.NO_CONTENT).body("data not found for "+accountId);
    }

    public ApiResponse getOrderInfo(Map<String,Object> params) {
        Long accountId = Long.parseLong(params.get("accountId").toString());
        Long orderId = Long.parseLong(params.get("orderId").toString());
        Optional<OrderEntity> data = Optional.ofNullable(orderEntityRepo.findByAccountAccountIdAndOrderId(accountId,orderId));
        if(data.isPresent()){
            OrderDto orderDtos = new OrderDto();
            OrderEntity order = data.get();
            orderDtos.setOrder(order);
            orderDtos.setOrderItems(order.getOrderItems());
            return ApiResponse.build(HttpStatus.OK).data(orderDtos).details("data found");
        }
        else
            return ApiResponse.build(HttpStatus.NO_CONTENT).body("data not found for "+accountId);
    }


    @Transactional
    public ApiResponse addOrder(OrderDto orderDto) {
        try {
            Long id = orderEntityRepo.findMaxId();
            OrderEntity savedOrderEntity  = orderDto.getOrder();
            savedOrderEntity.setOrderId(((id != null ? id : 1L)));
            savedOrderEntity.setOrderNo(String.valueOf (id != null ? id : 1L));
            for(OrderItemsEntity orderItemsEntity:orderDto.getOrderItems()){
                orderItemsEntity.setOrder(savedOrderEntity);
                savedOrderEntity.addOrderItem(orderItemsEntity);
            }
            orderEntityRepo.save(savedOrderEntity);

            orderDto.setOrder(savedOrderEntity);
            orderDto.setOrderItems(savedOrderEntity.getOrderItems());

            return ApiResponse.build(HttpStatus.CREATED).data(orderDto).details("Data Added Successfully");
        } catch (Exception ex) {
            return ApiResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage()).data(orderDto).error(ex);
        }
    }


//
//    @Transactional
//    public ApiResponse updateBooks(HashMap<String, Object> books) {
////    public void updateBooks(Books books){
//        Optional<BooksEntity> bookUpdOpt = booksEntityRepo.findById(Long.parseLong(books.get("booksId").toString()));
//        BeanWrapper wrapper = new BeanWrapperImpl(bookUpdOpt.get());
//
//        if (bookUpdOpt.isPresent()) {
//        books.keySet().forEach(k->{
//            try {
//                wrapper.getPropertyDescriptor(k.toString()).getWriteMethod();
//                wrapper.setPropertyValue(k.toString(), books.get(k.toString()));
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
////                    = books.get("title").toString();
//        });
//
////            if (books.containsKey("title")) {
////                bookUpdOpt.get().title = books.get("title").toString();
////            }
////            if (books.containsKey("author")) {
////                bookUpdOpt.get().author = books.get("author").toString();
////            }
////            if (books.containsKey("publisher")) {
////                bookUpdOpt.get().publisher = books.get("publisher").toString();
////            }
////            if (books.containsKey("edition")) {
////                bookUpdOpt.get().edition = books.get("edition").toString();
////            }
////            if (books.containsKey("numberOfPages")) {
////                bookUpdOpt.get().numberOfPages = Long.parseLong(books.get("numberOfPages").toString());
////            }
////            if (books.containsKey("country")) {
////                bookUpdOpt.get().country = books.get("country").toString();
////            }
////            if (books.containsKey("language")) {
////                bookUpdOpt.get().language = books.get("language").toString();
////            }
//            booksEntityRepo.save(bookUpdOpt.get());
//            return ApiResponse.build(HttpStatus.OK).body(bookUpdOpt.get()).details("Data Saved Successfully");
//        } else{
//        return ApiResponse.build(HttpStatus.NOT_FOUND).body(books).message("message Book not found");
//    }
//}
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
