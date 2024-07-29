package com.spring.henallux.ecommerce.Controller;

import com.spring.henallux.ecommerce.Model.*;
import com.spring.henallux.ecommerce.DataAccess.dao.*;
import com.spring.henallux.ecommerce.Service.Constants;
import com.spring.henallux.ecommerce.Service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Map;

@Controller
@RequestMapping(value = "/payment")
@SessionAttributes({Constants.CURRENT_ORDER})
public class PaymentController {

    private PaypalService paypalService;
    private OrderDataAccess orderDAO;
    private OrderLineDataAccess orderLineDAO;
    private ProductDataAccess productDAO;


    @Autowired
    public PaymentController(PaypalService paypalService, OrderDataAccess orderDAO, OrderLineDataAccess orderLineDAO, ProductDataAccess productDAO ) {
        this.paypalService = paypalService;
        this.orderDAO = orderDAO;
        this.orderLineDAO = orderLineDAO;
        this.productDAO = productDAO;
    }

    @Transactional
    @RequestMapping(value = "/paypal", method = RequestMethod.POST)
    public ResponseEntity<String> createPayment(@RequestParam(name = "orderId") int orderId, Authentication authentication) {
        Order order = orderDAO.findById(orderId);

        order.setOrderLines(orderLineDAO.findAllByOrderId(order));

        User user = (User) authentication.getPrincipal();
        // check if order belongs to user
        if (order.getUserId().getId() != user.getId())
            return ResponseEntity.ok("{\"status\": \"error\", \"message\": \"Your are not owner of order\"}");

        // if order null
        if (order == null)
            return ResponseEntity.ok("{\"status\": \"error\", \"message\": \"Order not found.\"}");

        System.out.println(order.getId());

        // if order status is not pending
        if (!order.getStatus().equals("Pending"))
            return ResponseEntity.ok("{\"status\": \"error\", \"message\": \"Order status is not pending.\"}");

        // create payment
        ResponseEntity<String> response = paypalService.createPayment(order);

        // get approval url
        return response;
    }

    @RequestMapping(value = "/paypal/capture/{paypalId}", method = RequestMethod.POST)
    public ResponseEntity<String> capturePayment(@PathVariable String paypalId, @RequestParam(name = "orderId") int orderId, Authentication authentication) {


        // capture payment
        ResponseEntity<String> response = paypalService.capturePayment(paypalId);

        // check if payment is successful
        if (response.getStatusCode().isError()) {
            return response;
        }

        Order order = orderDAO.findById(orderId);


        order.setStatus("Paid");

        orderDAO.save(order);

        return response;
    }

    @RequestMapping(value = "/paypal/success", method = RequestMethod.GET)
    public String successPayment(@ModelAttribute(value = Constants.CURRENT_CART) Cart cart, Authentication authentication) {

        return "integrated:success";
    }

    @RequestMapping(value = "/paypal/error", method = RequestMethod.GET)
    public String errorPayment() {
        return "integrated:error";
    }

    @RequestMapping(value = "/paypal/cancel", method = RequestMethod.POST)
    public ResponseEntity<String> cancelPayment(@RequestParam(name = "orderId") int orderId, Authentication authentication) {

        Order order = orderDAO.findById(orderId);
        order.setOrderLines(orderLineDAO.findAllByOrderId(order));

        order.setStatus("Canceled");

        orderDAO.save(order);

        for(Map.Entry<Integer, OrderLine> entry : order.getOrderLines().entrySet()){
            OrderLine orderLine = entry.getValue();

            Product product = orderLine.getProduct();
            product.setStock(product.getStock() + entry.getValue().getQuantity());
            productDAO.save(product);
        }

        String jsonResponse = "{\"status\": \"canceled\", \"message\": \"Payment has been canceled.\"}";

        // Renvoie la réponse formatée
        return ResponseEntity.ok(jsonResponse);
    }

    @RequestMapping(value = "/paypal/cancel", method = RequestMethod.GET)
    public String cancelPaymentGet() {
        return "integrated:cancel";
    }

}
