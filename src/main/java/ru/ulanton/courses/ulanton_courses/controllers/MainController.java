package ru.ulanton.courses.ulanton_courses.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ulanton.courses.ulanton_courses.repositories.CourseRepository;

@Controller
public class MainController {

    private final CourseRepository courseRepository;

    public MainController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            model.addAttribute("username", auth.getName());
            model.addAttribute("isAuthenticated", true);

            boolean isAdmin = auth.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            model.addAttribute("isAdmin", isAdmin);
        } else {
            model.addAttribute("isAuthenticated", false);
        }
        return "hello";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }

    @GetMapping("/errors/404")
    public String error404(Model model) {
        model.addAttribute("errorMessage", "Ара, ну нима пока такой страницы, выходи");
        return "error";
    }

    @GetMapping("/errors/500")
    public String error500(Model model) {
        model.addAttribute("errorMessage", "Внутренняя ошибка сервера");
        return "error";
    }

    @GetMapping("/errors/{code}")
    public String anyError(@PathVariable String code, Model model) {
        if ("404".equals(code)) {
            model.addAttribute("errorMessage", "Ара, ну нима пока такой страницы, выходи");
        } else {
            model.addAttribute("errorMessage", "Ошибка " + code);
        }
        return "error";
    }
}