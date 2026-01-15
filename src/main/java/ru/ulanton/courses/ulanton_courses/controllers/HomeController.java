package ru.ulanton.courses.ulanton_courses.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ulanton.courses.ulanton_courses.repositories.CourseRepository;

@Controller
public class HomeController {

    private final CourseRepository courseRepository;

    public HomeController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Главная (у тебя шаблон hello.html)
    @GetMapping({"/", "/hello"})
    public String hello() {
        return "hello";
    }

    // Каталог курсов (шаблон allCourses.html)
    @GetMapping("/allCourses")
    public String allCourses(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        return "allCourses";
    }

//    // Если у тебя есть шаблон about.html — оставь, если нет, можешь убрать этот метод
//    @GetMapping("/about")
//    public String about() {
//        return "about";
//    }
}
