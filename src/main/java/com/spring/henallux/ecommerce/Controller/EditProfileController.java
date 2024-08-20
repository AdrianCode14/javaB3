package com.spring.henallux.ecommerce.Controller;

import com.spring.henallux.ecommerce.Model.User;
import com.spring.henallux.ecommerce.Model.UserEditDto;
import com.spring.henallux.ecommerce.Model.PasswordChangeForm;
import com.spring.henallux.ecommerce.Service.UserService;
//import com.spring.henallux.ecommerce.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/profile")
public class EditProfileController {

    private final UserService userService;
    //private final OrderService orderService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EditProfileController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        //this.orderService = orderService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/edit")
    public String editProfile(Model model, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        UserEditDto userEditDto = userService.convertToUserEditDto(currentUser);

        model.addAttribute("user", userEditDto);
        model.addAttribute("passwordChangeForm", new PasswordChangeForm());
        //model.addAttribute("orders", orderService.findAllByUser(currentUser));

        return "integrated:editProfile";
    }

    @PostMapping("/edit")
    public String editProfileSubmit(@Valid @ModelAttribute("user") UserEditDto userEditDto,
                                    BindingResult bindingResult,
                                    Authentication authentication,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("passwordChangeForm", new PasswordChangeForm());
            return "integrated:editProfile";
        }

        User currentUser = (User) authentication.getPrincipal();

        if (!currentUser.getEmail().equals(userEditDto.getEmail()) && userService.findByEmail(userEditDto.getEmail()) != null) {
            model.addAttribute("error", "Email already exists");
            model.addAttribute("passwordChangeForm", new PasswordChangeForm());
            return "integrated:editProfile";
        }

        userService.updateUserProfile(currentUser, userEditDto);

        return "redirect:/profile/edit";
    }

    @PostMapping("/change-password")
    public String changePassword(@Valid @ModelAttribute("passwordChangeForm") PasswordChangeForm passwordChangeForm,
                                 BindingResult bindingResult,
                                 Authentication authentication,
                                 Model model) {
        User currentUser = (User) authentication.getPrincipal();

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userService.convertToUserEditDto(currentUser));
            return "integrated:editProfile";
        }

        if (!passwordEncoder.matches(passwordChangeForm.getOldPassword(), currentUser.getPassword())) {
            model.addAttribute("passwordError", "Old password is incorrect");
            model.addAttribute("user", userService.convertToUserEditDto(currentUser));
            return "integrated:editProfile";
        }

        if (!passwordChangeForm.getNewPassword().equals(passwordChangeForm.getNewPasswordConfirm())) {
            model.addAttribute("passwordError", "New passwords don't match");
            model.addAttribute("user", userService.convertToUserEditDto(currentUser));
            return "integrated:editProfile";
        }

        userService.updatePassword(currentUser, passwordChangeForm.getNewPassword());

        return "redirect:/profile/edit";
    }
}