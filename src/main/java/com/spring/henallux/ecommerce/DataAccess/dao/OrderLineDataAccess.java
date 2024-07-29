package com.spring.henallux.ecommerce.DataAccess.dao;

import com.spring.henallux.ecommerce.Model.Order;
import com.spring.henallux.ecommerce.Model.OrderLine;
import com.spring.henallux.ecommerce.DataAccess.entity.OrderEntity;

import java.util.HashMap;

public interface OrderLineDataAccess {
    void save(HashMap<Integer, OrderLine> orderLines, OrderEntity orderEntity);
    HashMap<Integer, OrderLine> findAllByOrderId(Order orderEntity);
}
