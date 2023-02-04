package com.example.ws1301.ws1301.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class HomeController {
    
    @GetMapping("/home")
    public String getHome() {
        return "index";
    }
}
