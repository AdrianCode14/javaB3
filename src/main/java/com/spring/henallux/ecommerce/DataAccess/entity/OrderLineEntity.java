package com.spring.henallux.ecommerce.DataAccess.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orders_lines")
@Getter
@Setter
public class OrderLineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_id")
    private int id;
    @Column(name = "quantity")
    @NotNull
    @Min(1)
    private int quantity;
    @OneToOne
    @NotNull
    @JoinColumn(name = "product_id")
    private ProductEntity productId;
    @OneToOne
    @JoinColumn(name = "order_id")
    @NotNull
    private OrderEntity orderId;
    @Column(name = "price")
    @Min(0)
    @NotNull
    private Double price;
}
