package com.myproject.restfultodolist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String helloworld(){
        return "Hello World";
    }
}
