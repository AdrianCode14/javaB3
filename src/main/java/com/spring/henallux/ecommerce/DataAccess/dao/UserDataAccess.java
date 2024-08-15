package com.spring.henallux.ecommerce.DataAccess.dao;

import com.spring.henallux.ecommerce.Model.User;

public interface UserDataAccess {
    User findByEmail(String email);
    User save(User user);
}