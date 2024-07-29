package com.spring.henallux.ecommerce.DataAccess.dao;

import com.spring.henallux.ecommerce.Model.Order;
import com.spring.henallux.ecommerce.Model.User;
import com.spring.henallux.ecommerce.DataAccess.entity.OrderEntity;
import com.spring.henallux.ecommerce.DataAccess.entity.UserEntity;
import com.spring.henallux.ecommerce.DataAccess.repository.OrderRepository;
import com.spring.henallux.ecommerce.DataAccess.util.ProviderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class OrderDAO implements OrderDataAccess {
    private OrderRepository orderRepository;
    private ProviderConverter providerConverter;
    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    public OrderDAO(OrderRepository orderRepository, ProviderConverter providerConverter) {
        this.orderRepository = orderRepository;
        this.providerConverter = providerConverter;
    }

    @Transactional
    public OrderEntity save(Order order) {
        OrderEntity orderEntity = providerConverter.orderToOrderEntity(order);

        orderEntity = entityManager.merge(orderEntity);
        orderEntity = orderRepository.save(orderEntity);

        return orderEntity;
    }

    public Order findById(int id) {
        OrderEntity orderEntity = orderRepository.findById(id);

        Order order = providerConverter.orderEntityToOrder(orderEntity);

        order.setUserId(providerConverter.userEntityToUser(orderEntity.getUserId()));

        return order;
    }

    public HashMap<Integer, Order> findAllByUserId(User user) {
        UserEntity userEntity = providerConverter.userToUserEntity(user);

        ArrayList<OrderEntity> orderEntities = orderRepository.findAllByUserId(userEntity);

        HashMap<Integer, Order> orders = new HashMap<>();

        for (OrderEntity orderEntity : orderEntities) {
            Order order = providerConverter.orderEntityToOrder(orderEntity);

            order.setUserId(providerConverter.userEntityToUser(orderEntity.getUserId()));

            orders.put(order.getId(), order);
        }

        return orders;
    }

    public Order findByPaypalOrderId(String paypalOrderId) {
        OrderEntity orderEntity = orderRepository.findByPaypalOrderId(paypalOrderId);

        Order order = providerConverter.orderEntityToOrder(orderEntity);

        order.setUserId(providerConverter.userEntityToUser(orderEntity.getUserId()));

        return order;
    }

}
