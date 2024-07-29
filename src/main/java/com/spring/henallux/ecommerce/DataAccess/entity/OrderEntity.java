package com.spring.henallux.ecommerce.DataAccess.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
@Setter
@Getter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;
    @Column(name = "date")
    @NotNull
    private Date date;
    @Column(name = "paypal_order_id")
    private String paypalOrderId;
    @Column(name = "order_status")
    @NotNull
    private String status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @NotNull
    private UserEntity userId;

}