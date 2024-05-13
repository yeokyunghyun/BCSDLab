package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JsonController {

    @GetMapping("/json")
    @ResponseBody
    public String json() {
        return "{\"name\": \"최준호\", \"age\": 25}";
    }
}
