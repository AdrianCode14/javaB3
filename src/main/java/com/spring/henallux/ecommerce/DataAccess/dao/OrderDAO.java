package com.spring.henallux.ecommerce.DataAccess.dao;

import com.spring.henallux.ecommerce.DataAccess.entity.OrderEntity;
import com.spring.henallux.ecommerce.DataAccess.entity.OrderLineEntity;
import com.spring.henallux.ecommerce.DataAccess.entity.ProductEntity;
import com.spring.henallux.ecommerce.DataAccess.entity.UserEntity;
import com.spring.henallux.ecommerce.DataAccess.repository.OrderRepository;
import com.spring.henallux.ecommerce.DataAccess.repository.OrderLineRepository;
import com.spring.henallux.ecommerce.DataAccess.repository.ProductRepository;
import com.spring.henallux.ecommerce.DataAccess.repository.UserRepository;
import com.spring.henallux.ecommerce.DataAccess.util.ProviderConverter;
import com.spring.henallux.ecommerce.Model.Cart;
import com.spring.henallux.ecommerce.Model.CartItem;
import com.spring.henallux.ecommerce.Model.Order;
import com.spring.henallux.ecommerce.Model.User;
import com.spring.henallux.ecommerce.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderDAO implements OrderDataAccess {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderLineRepository orderLineRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProviderConverter providerConverter;
    @Autowired
    private CartService cartService;

    @Transactional
    public Order createOrderFromCart(Cart cart, User user) {
        UserEntity userEntity = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + user.getUserId()));

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setDate(new Date());
        orderEntity.setOrderStatus("PENDING");
        orderEntity.setUser(userEntity);
        orderEntity.setTotalPrice(cartService.calculateTotalWithDiscount(cart));
        orderEntity.setOrderLines(new ArrayList<>()); // Initialiser la liste ici

        orderEntity = orderRepository.save(orderEntity);

        for (CartItem item : cart.getItems().values()) {
            OrderLineEntity orderLineEntity = new OrderLineEntity();
            orderLineEntity.setOrder(orderEntity);

            ProductEntity productEntity = productRepository.findById(item.getProduct().getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + item.getProduct().getProductId()));

            orderLineEntity.setProduct(productEntity);
            orderLineEntity.setQuantity(item.getQuantity());
            orderLineEntity.setUnitPrice(item.getProduct().getPrice());
            orderLineEntity = orderLineRepository.save(orderLineEntity);
            orderEntity.getOrderLines().add(orderLineEntity);
        }

        return providerConverter.orderEntityToOrder(orderEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> getOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(providerConverter::orderEntityToOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getOrdersByUser(User user) {
        UserEntity userEntity = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + user.getUserId()));
        return orderRepository.findByUser(userEntity).stream()
                .map(providerConverter::orderEntityToOrder)
                .collect(Collectors.toList());
    }

    @Override
    public Order updateOrderStatus(Integer orderId, String newStatus) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        orderEntity.setOrderStatus(newStatus);
        orderEntity = orderRepository.save(orderEntity);
        return providerConverter.orderEntityToOrder(orderEntity);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    @Transactional(readOnly = true)
    public double calculateTotalRevenue() {
        return orderRepository.findAll().stream()
                .mapToDouble(OrderEntity::getTotalPrice)
                .sum();
    }
}