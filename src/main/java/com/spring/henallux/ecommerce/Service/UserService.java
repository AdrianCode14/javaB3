package com.spring.henallux.ecommerce.Service;

import com.spring.henallux.ecommerce.DataAccess.dao.UserDataAccess;
import com.spring.henallux.ecommerce.Model.User;
import com.spring.henallux.ecommerce.Model.UserEditDto;
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

    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public UserEditDto convertToUserEditDto(User user) {
        UserEditDto dto = new UserEditDto();
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setDeliveryAddress(user.getDeliveryAddress());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setNickname(user.getNickname());
        dto.setFavoriteColor(user.getFavoriteColor());
        return dto;
    }

    public void updateUserProfile(User user, UserEditDto dto) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setDeliveryAddress(dto.getDeliveryAddress());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setNickname(dto.getNickname());
        user.setFavoriteColor(dto.getFavoriteColor());
        userDAO.save(user);
    }

    public void updatePassword(User user, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userDAO.save(user);
    }
}