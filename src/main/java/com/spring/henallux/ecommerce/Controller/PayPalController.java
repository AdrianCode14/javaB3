package com.spring.henallux.ecommerce.Controller;

import com.paypal.api.payments.Links;
import com.spring.henallux.ecommerce.Service.PaypalService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.util.List;

@Controller
@RequestMapping("/pay")
public class PayPalController {

    @Autowired
    private PaypalService paypalService;

    @PostMapping
    public RedirectView pay() {
        System.out.println("Pay method called");
        try {
            Payment payment = paypalService.createPayment(
                    20.00,
                    "USD",
                    "paypal",
                    "sale",
                    "Description du paiement",
                    "http://localhost:8081/firstSpring/cancel",
                    "http://localhost:8081/firstSpring/success"
            );

            System.out.println("Payment created successfully: " + payment);

            List<Links> links = payment.getLinks();
            for (Links link : links) {
                if (link.getRel().equals("approval_url")) {
                    System.out.println("Redirecting to approval URL: " + link.getHref());
                    return new RedirectView(link.getHref());
                }
            }

            System.err.println("Approval URL not found in payment links");
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            System.err.println("PayPalRESTException occurred: " + e.getMessage());
        }
        return new RedirectView("/");
    }

    @GetMapping("/cancel")
    public String cancelPay() {
        return "integrated:error";
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        // rediriger vers une page jsp avec paiement réussi et un lien pour retourner à l'accueil
        System.out.println("Payment succeeded with paymentId: " + paymentId + " and payerId: " + payerId);
        return "integrated:home";
    }
}
