package com.mohitul.blog_apps_demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {

    @GetMapping("/test")
    public String getPublicRouteTest() {
        System.out.println("Healthy!!");
        return "Healthy!!";
    }
}
