package com.spring.henallux.ecommerce.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer orderId;
    private LocalDateTime date;
    private String orderStatus;
    private User user;
    private Double totalPrice;
    private List<OrderLine> orderLines;
}