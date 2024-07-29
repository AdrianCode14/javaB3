package com.spring.henallux.ecommerce.DataAccess.repository;

import com.spring.henallux.ecommerce.DataAccess.entity.OrderEntity;
import com.spring.henallux.ecommerce.DataAccess.entity.OrderLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface OrderLineRepository extends JpaRepository<OrderLineEntity, String> {
    ArrayList<OrderLineEntity> findAllByOrderId(OrderEntity orderEntity);
}
