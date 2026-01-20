package ru.ulanton.courses.ulanton_courses.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @GetMapping("/")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            model.addAttribute("username", auth.getName());
            model.addAttribute("isAuthenticated", true);

            // Проверка ролей
            boolean isAdmin = auth.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            model.addAttribute("isAdmin", isAdmin);
        } else {
            model.addAttribute("isAuthenticated", false);
        }
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
