package com.spring.henallux.ecommerce.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {
    private Integer lineId;
    private Double unitPrice;
    private Integer quantity;
    private Order order;
    private Product product;
}