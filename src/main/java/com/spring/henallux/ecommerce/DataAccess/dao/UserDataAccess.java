package com.spring.henallux.ecommerce.DataAccess.dao;

import com.spring.henallux.ecommerce.Model.User;
import com.spring.henallux.ecommerce.Model.UserEdit;

public interface UserDataAccess {
    User findByEmail(String username);
    User save(User user);
    User update(UserEdit user, User oldUser);
}
