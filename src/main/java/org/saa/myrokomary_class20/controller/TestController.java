package org.saa.myrokomary_class20.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping(value = "/test-html")
    public String getTestData(Model model){
        return "testhtml";
    }
}
