package com.spring.henallux.ecommerce.DataAccess.dao;

import com.spring.henallux.ecommerce.Model.User;
import com.spring.henallux.ecommerce.Model.UserEdit;
import com.spring.henallux.ecommerce.DataAccess.entity.UserEntity;
import com.spring.henallux.ecommerce.DataAccess.repository.UserRepository;
import com.spring.henallux.ecommerce.DataAccess.util.ProviderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDAO implements UserDataAccess {

    private UserRepository userRepository;
    private ProviderConverter providerConverter;

    @Autowired
    public UserDAO(UserRepository userRepository, ProviderConverter providerConverter) {
        this.userRepository = userRepository;
        this.providerConverter = providerConverter;
    }

    @Override
    public User findByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        return userEntity != null ? providerConverter.userEntityToUser(userEntity) : null;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = providerConverter.userToUserEntity(user);
        userEntity = userRepository.save(userEntity);
        return providerConverter.userEntityToUser(userEntity);
    }
}