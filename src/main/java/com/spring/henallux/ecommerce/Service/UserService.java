package com.spring.henallux.ecommerce.Service;

import com.spring.henallux.ecommerce.DataAccess.dao.UserDataAccess;
import com.spring.henallux.ecommerce.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDataAccess userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDataAccess userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        if (userDAO.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.save(user);
    }

    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public User updateUser(User user) {
        User existingUser = userDAO.findByEmail(user.getEmail());
        if (existingUser == null) {
            throw new RuntimeException("User not found");
        }

        // Update user fields here
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setDeliveryAddress(user.getDeliveryAddress());
        existingUser.setPhoneNumber(user.getPhoneNumber());

        return userDAO.save(existingUser);
    }
}