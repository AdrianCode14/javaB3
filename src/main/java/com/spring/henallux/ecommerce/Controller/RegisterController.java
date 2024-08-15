package com.spring.henallux.ecommerce.Controller;

import com.spring.henallux.ecommerce.Model.UserRegistrationDto;
import com.spring.henallux.ecommerce.Model.User;
import com.spring.henallux.ecommerce.DataAccess.dao.UserDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value="/register")
public class RegisterController {

    private final UserDataAccess userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterController(UserDataAccess userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String register(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "integrated:register";
    }

    @PostMapping
    public String registerSubmit(@Valid @ModelAttribute("user") UserRegistrationDto userDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "integrated:register";
        }

        if (!userDto.getPassword().equals(userDto.getPasswordConfirm())) {
            bindingResult.rejectValue("passwordConfirm", "error.user", "Les mots de passe ne correspondent pas");
            return "integrated:register";
        }

        if (userDAO.findByEmail(userDto.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.user", "Cet email est déjà utilisé");
            return "integrated:register";
        }

        User user = convertToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setAuthorities("ROLE_USER");

        userDAO.save(user);

        redirectAttributes.addFlashAttribute("successMessage", "Inscription réussie. Veuillez vous connecter.");
        return "redirect:/login";
    }

    private User convertToUser(UserRegistrationDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setDeliveryAddress(dto.getDeliveryAddress());
        user.setPhoneNumber(dto.getPhoneNumber());
        return user;
    }
}