package com.spring.henallux.ecommerce.DataAccess.dao;

import com.spring.henallux.ecommerce.Model.Cart;
import com.spring.henallux.ecommerce.Model.Order;
import com.spring.henallux.ecommerce.Model.User;

import java.util.List;
import java.util.Optional;

public interface OrderDataAccess {
    Order createOrderFromCart(Cart cart, User user);
    Optional<Order> getOrderById(Integer orderId);
    List<Order> getOrdersByUser(User user);
    Order updateOrderStatus(Integer orderId, String newStatus);
    void deleteOrder(Integer orderId);
    double calculateTotalRevenue();
}