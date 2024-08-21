package com.spring.henallux.ecommerce.DataAccess.repository;

import com.spring.henallux.ecommerce.DataAccess.entity.OrderEntity;
import com.spring.henallux.ecommerce.DataAccess.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findByUser(UserEntity user);
}