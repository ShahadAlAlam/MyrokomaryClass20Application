package org.saa.myrokomary_class20.controller;

import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.services.BooksService;
import org.saa.myrokomary_class20.services.BooksServiceDbImpl;
import org.saa.myrokomary_class20.services.BooksServiceInternalImpl;
import org.saa.myrokomary_class20.utils.ApiResponse;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class LoginController {
    @GetMapping(value="/basicauth")
    public String basicAuthCheck(){

        return "hello world";
    }

}
