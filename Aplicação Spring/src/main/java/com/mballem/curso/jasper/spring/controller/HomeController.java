package com.mballem.curso.jasper.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Connection;

@Controller
public class HomeController {



    @GetMapping("/")
    public String index() {
        return "index";
    }

}
