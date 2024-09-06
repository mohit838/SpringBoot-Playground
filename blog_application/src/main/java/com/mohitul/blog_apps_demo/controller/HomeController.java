package com.mohitul.blog_apps_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HomeController {

    @GetMapping("/")
    public String getPublicRouteTest() {
        System.out.println("Healthy!!");
        return "Healthy!!";
    }
}
