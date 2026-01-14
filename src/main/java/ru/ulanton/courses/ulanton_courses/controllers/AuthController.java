package ru.ulanton.courses.ulanton_courses.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage(){
        return "log-in"; // Вернет login.html из templates/
    }
}