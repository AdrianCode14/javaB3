package com.spring.henallux.ecommerce.Controller;

import org.springframework.ui.Model;  // Changez cet import
import com.spring.henallux.ecommerce.DataAccess.entity.OrderEntity;
import com.spring.henallux.ecommerce.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/confirmation")
    public String orderConfirmation(@RequestParam("orderId") Integer orderId, Model model) {
        OrderEntity order = orderService.getOrderById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        model.addAttribute("order", order);
        return "orderConfirmation";
    }

    @GetMapping("/failed")
    public String orderFailed(@RequestParam("orderId") Integer orderId, Model model) {
        OrderEntity order = orderService.getOrderById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        model.addAttribute("order", order);
        return "orderFailed";
    }

    @GetMapping("/cancelled")
    public String orderCancelled(@RequestParam("orderId") Integer orderId, Model model) {
        OrderEntity order = orderService.getOrderById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        model.addAttribute("order", order);
        return "orderCancelled";
    }
}