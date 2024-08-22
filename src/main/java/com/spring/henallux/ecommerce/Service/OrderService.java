package com.spring.henallux.ecommerce.Service;

import com.spring.henallux.ecommerce.DataAccess.dao.OrderDataAccess;
import com.spring.henallux.ecommerce.Model.Cart;
import com.spring.henallux.ecommerce.Model.Order;
import com.spring.henallux.ecommerce.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderDataAccess orderDAO;

    @Transactional
    public Order createOrderFromCart(Cart cart, User user) {
        return orderDAO.createOrderFromCart(cart, user);
    }

    @Transactional(readOnly = true)
    public Optional<Order> getOrderById(Integer orderId) {
        return orderDAO.getOrderById(orderId);
    }

    @Transactional(readOnly = true)
    public List<Order> getOrdersByUser(User user) {
        return orderDAO.getOrdersByUser(user);
    }

    @Transactional
    public Order updateOrderStatus(Integer orderId, String newStatus) {
        return orderDAO.updateOrderStatus(orderId, newStatus);
    }

    @Transactional
    public void deleteOrder(Integer orderId) {
        orderDAO.deleteOrder(orderId);
    }

    @Transactional(readOnly = true)
    public double calculateTotalRevenue() {
        return orderDAO.calculateTotalRevenue();
    }
}