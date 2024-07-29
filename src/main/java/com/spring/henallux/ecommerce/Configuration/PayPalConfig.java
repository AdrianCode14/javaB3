package com.spring.henallux.ecommerce.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class PayPalConfig {

    @Value("${paypal.clientId}")
    private String clientId;

    @Value("${paypal.secret}")
    private String secret;

    @Value("${paypal.base-url}")
    private String baseUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public ResponseEntity<String> makePayPalApiCall(String endpoint, HttpMethod method, HttpHeaders headers, HttpEntity<?> entity) {
        String apiUrl = baseUrl + endpoint;

        if (headers == null) {
            headers = createHeaders();
        }

        return restTemplate().exchange(apiUrl, method, entity, String.class);
    }

    public String getAccessToken() {
        String apiUrl = "/v1/oauth2/token";
        HttpHeaders headers = createHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        ResponseEntity<String> response = makePayPalApiCall(apiUrl, HttpMethod.POST, headers, new HttpEntity<>(body, headers));

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            return extractAccessToken(responseBody);
        } else {
            handleAccessTokenError(response);
            return null;
        }
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(clientId, secret);
        return headers;
    }

    private String extractAccessToken(String responseBody) {
        return responseBody.split("\"access_token\":\"")[1].split("\"")[0];
    }

    private void handleAccessTokenError(ResponseEntity<String> response) {
        // Gestion des erreurs, si nécessaire
        System.out.println("Erreur lors de la récupération du jeton d'accès PayPal. Code de statut : " + response.getStatusCode());
        // Autres actions de gestion des erreurs...
    }
}
