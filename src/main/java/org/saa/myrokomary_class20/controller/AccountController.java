package org.saa.myrokomary_class20.controller;

import org.saa.myrokomary_class20.entity.AccountEntity;
import org.saa.myrokomary_class20.entity.OrderEntity;
import org.saa.myrokomary_class20.services.AccountService;
import org.saa.myrokomary_class20.services.OrderService;
import org.saa.myrokomary_class20.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

//    @GetMapping(value="/basicauth")
//    public ApiResponse basicAuth(){
//
//        return ApiResponse.build(HttpStatus.OK).body("successfull");
//    }
//    @GetMapping(value="/")
    @CrossOrigin
    @PostMapping(value="/add-account")
    public ApiResponse addOrder(@RequestBody AccountEntity accountEntity){
        return ApiResponse.build(HttpStatus.OK).body(accountService.addAccunt(accountEntity));
    }

}
