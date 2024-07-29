package com.spring.henallux.ecommerce.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderLine {
    private int id;
    private int quantity;
    private double price;
    private Product product;
    private Order order;

    public double getTotalPrice() {
        return Math.round((quantity * price) * 100.0) / 100.0;
    }
}
