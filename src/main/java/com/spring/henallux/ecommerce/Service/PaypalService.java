package com.spring.henallux.ecommerce.Service;

import com.spring.henallux.ecommerce.Model.Order;
import com.spring.henallux.ecommerce.Configuration.PayPalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

@Service
public class PaypalService {
    private final PayPalConfig payPalConfig;

    @Autowired
    public PaypalService(PayPalConfig payPalConfig) {
        this.payPalConfig = payPalConfig;
    }

    public ResponseEntity<String> createPayment(Order order) {
        String apiUrl = "/v2/checkout/orders";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String accessToken = payPalConfig.getAccessToken();
        headers.setBearerAuth(accessToken);

        // Construire le corps de la requête pour créer un paiement avec les articles de la commande
        String requestBody = buildRequestBody(order);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        return payPalConfig.makePayPalApiCall(apiUrl, HttpMethod.POST, headers, entity);
    }

    public ResponseEntity<String> capturePayment(String orderId) {
        String apiUrl = "/v2/checkout/orders/" + orderId + "/capture";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String accessToken = payPalConfig.getAccessToken();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return payPalConfig.makePayPalApiCall(apiUrl, HttpMethod.POST, headers, entity);
    }

    private String buildRequestBody(Order order) {
        StringBuilder json = new StringBuilder(
                        "{"
                        + "\"intent\": \"CAPTURE\","
                        + "\"purchase_units\": ["
                        + "{"
                        + "\"amount\": {"
                        + "\"currency_code\": \"EUR\","
                        + "\"value\": " + order.getTotalPriceWithShippingCost()
                        + "}"
                        + "}"
                        + "],"
                        + "\"payment_source\": {"
                        + "\"paypal\": {"
                        + "\"experience_context\": {"
                        + "\"payment_method_preference\": \"IMMEDIATE_PAYMENT_REQUIRED\","
                        + "\"brand_name\": \"Furniture paradise\","
                        + "\"landing_page\": \"LOGIN\","
                        + "\"user_action\": \"PAY_NOW\""
                        + "}"
                        + "}"
                        + "}"
                        + "}");

        return json.toString();
    }
}
