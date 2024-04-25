package org.saa.myrokomary_class20.controller;

import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.dto.OrderDto;
import org.saa.myrokomary_class20.entity.OrderEntity;
import org.saa.myrokomary_class20.services.BooksService;
import org.saa.myrokomary_class20.services.BooksServiceDbImpl;
import org.saa.myrokomary_class20.services.BooksServiceInternalImpl;
import org.saa.myrokomary_class20.services.OrderService;
import org.saa.myrokomary_class20.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

//    @GetMapping(value="/basicauth")
//    public ApiResponse basicAuth(){
//
//        return ApiResponse.build(HttpStatus.OK).body("successfull");
//    }
//    @GetMapping(value="/")

    //    @CrossOrigin
    @PostMapping(value="/add-order")
    public ApiResponse getAllOrders(@RequestBody OrderDto orderEntity){
        return orderService.addOrder(orderEntity);
    }

    @GetMapping(value="/get-orders/{accountId}")
    public ApiResponse getAllOrders(@PathVariable(name="accountId") Long accountId ){
        return orderService.getAllOrders(accountId);
    }

}
