package com.spring.henallux.ecommerce.Controller;

import com.spring.henallux.ecommerce.Model.User;
import com.spring.henallux.ecommerce.DataAccess.dao.UserDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value="/register")
public class RegisterController {

    private UserDataAccess userDAO;

    @Autowired
    public RegisterController(UserDataAccess userDAO){
        this.userDAO = userDAO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "integrated:register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerSubmit(Model model, @Valid @ModelAttribute(value="user") User user, final BindingResult errors){

        System.out.println("test console");
        if(errors.hasErrors()){
            model.addAttribute("user", user);
            return "integrated:register";
        }
        //check match password
        if(!user.getPassword().equals(user.getPasswordConfirm())){
            model.addAttribute("user", user);
            model.addAttribute("error", "Password doesn't match");
            return "integrated:register";
        }



        //check if user already exist
        if(userDAO.findByEmail(user.getEmail()) != null){
            model.addAttribute("user", user);
            model.addAttribute("error", "User already exist");
            return "integrated:register";
        }



        //crypt password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(hashedPassword);

        user.setAuthorities("ROLE_USER");
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        //save to db
        userDAO.save(user);
        System.out.println("insription reussi");
        return "redirect:/login";
    }
}
