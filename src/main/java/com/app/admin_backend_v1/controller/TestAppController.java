package com.app.admin_backend_v1.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestAppController {

    @GetMapping
    public String test(){
        return "it's fucking working fine";
    }

}
