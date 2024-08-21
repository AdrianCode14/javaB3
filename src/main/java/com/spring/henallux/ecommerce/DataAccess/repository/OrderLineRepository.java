package com.spring.henallux.ecommerce.DataAccess.repository;

import com.spring.henallux.ecommerce.DataAccess.entity.OrderLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLineEntity, Integer> {
}