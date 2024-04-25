package org.saa.myrokomary_class20.controller;

import org.saa.myrokomary_class20.entity.AccountAddress;
import org.saa.myrokomary_class20.entity.AccountEntity;
import org.saa.myrokomary_class20.services.AccountAddressService;
import org.saa.myrokomary_class20.services.AccountService;
import org.saa.myrokomary_class20.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountAddressController {
    @Autowired
    private AccountAddressService serv;

//    @GetMapping(value="/basicauth")
//    public ApiResponse basicAuth(){
//
//        return ApiResponse.build(HttpStatus.OK).body("successfull");
//    }
//    @GetMapping(value="/")
    @CrossOrigin
    @PostMapping(value="/add-account-address")
    public ApiResponse addOrder(@RequestBody AccountAddress accountAddress){
        return ApiResponse.build(HttpStatus.OK).body(serv.addAccuntAddress(accountAddress));
    }

}
