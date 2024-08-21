package com.spring.henallux.ecommerce.Controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.spring.henallux.ecommerce.DataAccess.entity.OrderEntity;
import com.spring.henallux.ecommerce.DataAccess.entity.UserEntity;
import com.spring.henallux.ecommerce.Model.Order;
import com.spring.henallux.ecommerce.Model.OrderStatus;
import com.spring.henallux.ecommerce.Model.User;
import com.spring.henallux.ecommerce.Model.Cart;
import com.spring.henallux.ecommerce.Service.CartService;
import com.spring.henallux.ecommerce.Service.OrderService;
import com.spring.henallux.ecommerce.Service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/pay")
public class PayPalController {

    @Autowired
    private PaypalService paypalService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @PostMapping
    public RedirectView pay(HttpSession session) {
        Cart cart = cartService.getCart(session);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        try {
            // Créer la commande
            OrderEntity order = orderService.createOrderFromCart(cart, user);

            // Créer le paiement PayPal
            Payment payment = paypalService.createPayment(
                    order.getTotalPrice(),
                    "USD",
                    "paypal",
                    "sale",
                    "Payment for order " + order.getOrderId(),
                    "http://localhost:8081/firstSpring/pay/cancel",
                    "http://localhost:8081/firstSpring/pay/success"
            );

            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return new RedirectView(link.getHref());
                }
            }

        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return new RedirectView("/error");
    }

    @GetMapping("/cancel")
    public String cancelPay(@RequestParam("orderId") Integer orderId) {
        orderService.updateOrderStatus(orderId, OrderStatus.CANCELLED);
        return "redirect:/order/cancelled?orderId=" + orderId;
    }

    @GetMapping("/success")
    public String successPay(@RequestParam("paymentId") String paymentId,
                             @RequestParam("PayerID") String payerId,
                             @RequestParam("orderId") Integer orderId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                orderService.updateOrderStatus(orderId, OrderStatus.SHIPPED);
                return "redirect:/order/confirmation?orderId=" + orderId;
            } else {
                orderService.updateOrderStatus(orderId, OrderStatus.FAILED);
                return "redirect:/order/failed?orderId=" + orderId;
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            orderService.updateOrderStatus(orderId, OrderStatus.FAILED);
            return "redirect:/order/failed?orderId=" + orderId;
        }
    }
}