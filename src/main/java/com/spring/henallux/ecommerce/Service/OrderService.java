package com.spring.henallux.ecommerce.Service;

import com.spring.henallux.ecommerce.DataAccess.entity.OrderEntity;
import com.spring.henallux.ecommerce.DataAccess.entity.OrderLineEntity;
import com.spring.henallux.ecommerce.DataAccess.entity.ProductEntity;
import com.spring.henallux.ecommerce.DataAccess.entity.UserEntity;
import com.spring.henallux.ecommerce.DataAccess.repository.ProductRepository;
import com.spring.henallux.ecommerce.DataAccess.repository.UserRepository;
import com.spring.henallux.ecommerce.Model.Cart;
import com.spring.henallux.ecommerce.Model.CartItem;
import com.spring.henallux.ecommerce.DataAccess.repository.OrderRepository;
import com.spring.henallux.ecommerce.DataAccess.repository.OrderLineRepository;
import com.spring.henallux.ecommerce.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public OrderEntity createOrderFromCart(Cart cart, User user) {
        UserEntity userEntity = getUserEntity(user);

        OrderEntity order = new OrderEntity();
        order.setDate(new Date());
        order.setOrderStatus("PENDING");
        order.setUser(userEntity);
        order.setTotalPrice(cart.calculateTotal());

        order = orderRepository.save(order);

        for (CartItem item : cart.getItems().values()) {
            OrderLineEntity orderLine = new OrderLineEntity();
            orderLine.setOrder(order);

            ProductEntity productEntity = productRepository.findById(item.getProduct().getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + item.getProduct().getProductId()));

            orderLine.setProduct(productEntity);
            orderLine.setQuantity(item.getQuantity());
            orderLine.setUnitPrice(item.getProduct().getPrice());
            orderLineRepository.save(orderLine);
        }

        return order;
    }

    private UserEntity getUserEntity(User user) {
        return userRepository.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + user.getUserId()));
    }

    @Transactional(readOnly = true)
    public Optional<OrderEntity> getOrderById(Integer orderId) {
        return orderRepository.findById(orderId);
    }

    @Transactional(readOnly = true)
    public List<OrderEntity> getOrdersByUser(UserEntity user) {
        return orderRepository.findByUser(user);
    }

    @Transactional
    public OrderEntity updateOrderStatus(Integer orderId, String newStatus) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        order.setOrderStatus(newStatus);
        return orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    @Transactional(readOnly = true)
    public double calculateTotalRevenue() {
        return orderRepository.findAll().stream()
                .mapToDouble(OrderEntity::getTotalPrice)
                .sum();
    }
}