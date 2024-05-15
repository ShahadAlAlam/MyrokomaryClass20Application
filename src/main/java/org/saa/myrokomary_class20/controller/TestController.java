package org.saa.myrokomary_class20.controller;

import org.saa.myrokomary_class20.entity.TestEntity;
import org.saa.myrokomary_class20.services.TestServiceDbImpl;
import org.saa.myrokomary_class20.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/*
* This Controller is for Dynamic Update
* */
@RestController
public class TestController {

    @Autowired
    private TestServiceDbImpl testServiceDb;


    @PostMapping(value = "/addData")
    public ApiResponse addTestData(@RequestBody TestEntity testEntity){
        return ApiResponse.build(HttpStatus.OK).data(testServiceDb.addTestData( testEntity));
    }
    @PutMapping(value = "/updateData")
    public ApiResponse updateTestData(@RequestBody HashMap<String,Object> testEntity){
        return ApiResponse.build(HttpStatus.OK).data(testServiceDb.updateTestData( testEntity));
    }
    @PutMapping(value = "/updateDataSpl")
    public ApiResponse updateTestDataWithSpecial(@RequestBody HashMap<String,Object> testEntity){
        return ApiResponse.build(HttpStatus.OK).data(testServiceDb.updateTestDataWithSpecial( testEntity));
    }
}
