package com.example.springdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello to world");
        return "hello to huhuan!!!!";
    }
}
