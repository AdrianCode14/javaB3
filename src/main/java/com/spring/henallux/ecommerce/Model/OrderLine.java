package com.spring.henallux.ecommerce.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderLine {
    private Integer lineId;
    private Double unitPrice;
    private Integer quantity;
    private Order order;
    private Product product;
}