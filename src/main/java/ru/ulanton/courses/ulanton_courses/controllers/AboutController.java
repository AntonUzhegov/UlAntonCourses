package ru.ulanton.courses.ulanton_courses.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
