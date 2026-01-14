package ru.ulanton.courses.ulanton_courses.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/errors")
public class SimpleErrorController {

    @GetMapping("/404")
    public String error404(Model model) {
        model.addAttribute("errorMessage", "Ара, ну нима пока такой страницы, выходи");
        return "error";
    }

    @GetMapping("/500")
    public String error500(Model model) {
        model.addAttribute("errorMessage", "Внутренняя ошибка сервера");
        return "error";
    }

    @GetMapping("/{code}")
    public String anyError(@PathVariable String code, Model model) {
        if ("404".equals(code)) {
            model.addAttribute("errorMessage", "Ара, ну нима пока такой страницы, выходи");
        } else {
            model.addAttribute("errorMessage", "Ошибка " + code);
        }
        return "error";
    }
}