package com.spring.henallux.ecommerce.DataAccess.dao;

import com.spring.henallux.ecommerce.Model.Order;
import com.spring.henallux.ecommerce.Model.User;
import com.spring.henallux.ecommerce.DataAccess.entity.OrderEntity;

import java.util.HashMap;

public interface OrderDataAccess {
    OrderEntity save(Order order);

    Order findById(int id);

    HashMap<Integer, Order> findAllByUserId(User user);

    Order findByPaypalOrderId(String paypalOrderId);
}
