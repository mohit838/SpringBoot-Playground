package com.mohitul.blog_apps_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/users")
    public String getUsers() {
        System.out.println("Getting Users.");
        return "User";
    }
}
