package ru.ulanton.courses.ulanton_courses.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("appName", "UlAntonCourses");
        model.addAttribute("now", LocalDateTime.now());
        return "home"; // templates/home.html
    }

    @GetMapping("/courses")
    public String courses() {
        return "courses";
    }
}
