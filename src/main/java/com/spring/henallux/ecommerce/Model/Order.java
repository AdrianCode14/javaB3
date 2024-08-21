package com.spring.henallux.ecommerce.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Order {
    private Integer orderId;
    private Date date;
    private String orderStatus;
    private User user;
    private Double totalPrice;
    private List<OrderLine> orderLines;
}