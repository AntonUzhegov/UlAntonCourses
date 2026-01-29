package ru.ulanton.courses.ulanton_courses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ulanton.courses.ulanton_courses.models.User;
import ru.ulanton.courses.ulanton_courses.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Проверка аутентификации
    private boolean isUserAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
    }

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout,
                                @RequestParam(value = "registered", required = false) String registered,
                                Model model) {

        if (isUserAuthenticated()) {
            return "redirect:/";
        }

        if (error != null) model.addAttribute("error", true);
        if (logout != null) model.addAttribute("logout", true);
        if (registered != null) model.addAttribute("registered", true);

        return "log-in";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        if (isUserAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user,
                               @RequestParam("confirmPassword") String confirmPassword,
                               Model model) {

        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Пароли не совпадают");
            model.addAttribute("user", user);
            return "register";
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Пользователь с таким email уже существует");
            model.addAttribute("user", user);
            return "register";
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "Пользователь с таким именем уже существует");
            model.addAttribute("user", user);
            return "register";
        }

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            Set<String> roles = new HashSet<>();
            roles.add("USER");
            user.setRoles(roles);

            userRepository.save(user);
            return "redirect:/login?registered=true";
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка при регистрации: " + e.getMessage());
            model.addAttribute("user", user);
            return "register";
        }
    }
}