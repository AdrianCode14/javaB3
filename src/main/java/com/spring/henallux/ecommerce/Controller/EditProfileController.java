package com.spring.henallux.ecommerce.Controller;
/*
import com.spring.henallux.ecommerce.Model.Order;
import com.spring.henallux.ecommerce.Model.PasswordChangeForm;
import com.spring.henallux.ecommerce.Model.User;
import com.spring.henallux.ecommerce.Model.UserEdit;
import com.spring.henallux.ecommerce.DataAccess.dao.OrderDataAccess;
import com.spring.henallux.ecommerce.DataAccess.dao.OrderLineDataAccess;
import com.spring.henallux.ecommerce.DataAccess.dao.UserDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(value = "/editProfile")
public class EditProfileController {

    private UserDataAccess userDAO;
    private OrderDataAccess orderDAO;
    private OrderLineDataAccess orderLineDAO;

    @Autowired
    public EditProfileController(UserDataAccess userDAO, OrderDataAccess orderDAO, OrderLineDataAccess orderLineDA) {
        this.userDAO = userDAO;
        this.orderDAO = orderDAO;
        this.orderLineDAO = orderLineDA;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String editProfile(Model model, Authentication authentication) {
        User oldUser = (User) authentication.getPrincipal();

        UserEdit user = new UserEdit();

        user.setEmail(oldUser.getEmail());
        user.setFirstName(oldUser.getFirstName());
        user.setLastName(oldUser.getLastName());
        user.setDeliveryAddress(oldUser.getDeliveryAddress());
        user.setPhoneNumber(oldUser.getPhoneNumber());

        model.addAttribute("user", user);


        User userInDb = userDAO.findByEmail(oldUser.getEmail());

        HashMap<Integer, Order> orders = orderDAO.findAllByUserId(userInDb);

        for (Order order : orders.values()) {
            order.setOrderLines(orderLineDAO.findAllByOrderId(order));
        }

        orders = sortByDate(orders);

        model.addAttribute("orders", orders);
        model.addAttribute("passwordchangeform", new PasswordChangeForm());

        return "integrated:editProfile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String editProfileSubmit(Model model, @Valid @ModelAttribute(value = "user") UserEdit user, Authentication authentication, final BindingResult errors) {
        User oldUser = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("passwordchangeform", new PasswordChangeForm());
        //check
        if (errors.hasErrors()) {
            model.addAttribute("user", user);
            return "integrated:editProfile";
        }


        //check email already exist
        if (!oldUser.getEmail().equals(user.getEmail()) && userDAO.findByEmail(user.getEmail()) != null) {
            model.addAttribute("user", user);
            model.addAttribute("error", "Email already exist");
            return "integrated:editProfile";
        }

        //save to db
        oldUser = userDAO.update(user, oldUser);

        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(oldUser, authentication.getCredentials(), oldUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);

        return "integrated:home";
    }


    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public String editPasswordSubmit(Model model, @Valid @ModelAttribute(value = "user") UserEdit user, @Valid @ModelAttribute(value = "passwordchangeform") PasswordChangeForm passwordChangeForm, Authentication authentication, final BindingResult errors) {
        User oldUser = (User) authentication.getPrincipal();
        model.addAttribute("passwordchangeform", passwordChangeForm);
        model.addAttribute("user", oldUser);
        System.out.println(passwordChangeForm.getOldPassword());
        //check
        if (errors.hasErrors()) {
            model.addAttribute("passwordchangeform", new PasswordChangeForm());

            return "integrated:editProfile";
        }

        //check old password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(passwordChangeForm.getOldPassword(), oldUser.getPassword())) {
            model.addAttribute("passwordchangeform", new PasswordChangeForm());
            model.addAttribute("error", "Old password is incorrect");
            return "integrated:editProfile";
        }


        //check new password
        if (!passwordChangeForm.getNewPassword().equals(passwordChangeForm.getNewPasswordConfirm())) {
            model.addAttribute("passwordchangeform", new PasswordChangeForm());
            model.addAttribute("error", "New password doesn't match");
            return "integrated:editProfile";
        }

        //crypt password
        String hashedPassword = passwordEncoder.encode(passwordChangeForm.getNewPassword());

        oldUser.setPassword(hashedPassword);

        //save to db
        oldUser = userDAO.update(user, oldUser);

        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(oldUser, authentication.getCredentials(), oldUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);

        return "integrated:home";
    }

    private HashMap<Integer, Order> sortByDate(HashMap<Integer, Order> orders) {
        // Create a list from the entries of the original HashMap
        List<Map.Entry<Integer, Order>> entryList = new ArrayList<>(orders.entrySet());

        // Sort the list based on the order date using a custom comparator
        entryList.sort(Comparator.comparing(entry -> entry.getValue().getDate()));

        // Create a new LinkedHashMap to maintain the order
        HashMap<Integer, Order> sortedOrders = new LinkedHashMap<>();
        for (Map.Entry<Integer, Order> entry : entryList) {
            sortedOrders.put(entry.getKey(), entry.getValue());
        }

        return sortedOrders;
    }
}
*/