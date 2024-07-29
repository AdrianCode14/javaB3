package com.spring.henallux.ecommerce.Service;

import com.spring.henallux.ecommerce.Model.User;
import com.spring.henallux.ecommerce.DataAccess.dao.UserDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    private UserDataAccess userDAO;

    @Autowired
    public UserDetailsServiceImplementation(UserDataAccess userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(email);

        if (user !=null) {
            return user;
        }

        throw new UsernameNotFoundException("User not found");
    }
}
