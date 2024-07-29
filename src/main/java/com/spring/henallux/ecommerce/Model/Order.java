package com.spring.henallux.ecommerce.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class Order {
    private int id;
    private Date date;
    private String status;
    private String paypalOrderId;
    private User userId;
    private HashMap<Integer, OrderLine> orderLines;

    public double getTotalPrice() {
        double totalPrice = 0;
        for (OrderLine orderLine : orderLines.values()) {
            totalPrice += orderLine.getPrice() * orderLine.getQuantity();
        }
        totalPrice = Math.round(totalPrice * 100.0) / 100.0;

        return totalPrice;
    }

    public double getTotalPriceWithShippingCost() {
        double totalPrice = 0;
        for (OrderLine orderLine : orderLines.values()) {
            totalPrice += orderLine.getPrice() * orderLine.getQuantity();
        }
        totalPrice += 5;
        totalPrice = Math.round(totalPrice * 100.0) / 100.0;
        return totalPrice;
    }

    public String getStatusIcon() {
        if (status.equals("Canceled"))
            return "/assets/icons/canceled.png";
        if (status.equals("Paid"))
            return "/assets/icons/valid.png";
        return "/assets/icons/pending.png";
    }

}
