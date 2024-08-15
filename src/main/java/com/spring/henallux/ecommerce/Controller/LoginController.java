package com.spring.henallux.ecommerce.Controller;

import com.spring.henallux.ecommerce.Model.User;
import com.spring.henallux.ecommerce.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showLoginForm(Model model,
                                @RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "success", required = false) String success) {
        model.addAttribute("user", new User());

        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password");
        }

        if (success != null) {
            model.addAttribute("successMessage", "Registration successful. Please log in.");
        }

        return "integrated:login";
    }

    @GetMapping("/success")
    public String loginSuccess() {
        // Logique post-connexion si nécessaire
        return "redirect:/"; // Redirection vers la page d'accueil après connexion réussie
    }
}