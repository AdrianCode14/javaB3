package com.spring.henallux.ecommerce.DataAccess.repository;

import com.spring.henallux.ecommerce.DataAccess.entity.OrderEntity;
import com.spring.henallux.ecommerce.DataAccess.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    OrderEntity findById(int id);

    ArrayList<OrderEntity> findAllByUserId(UserEntity userEntity);

    OrderEntity findByPaypalOrderId(String paypalOrderId);
}
