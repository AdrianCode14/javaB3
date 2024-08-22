package com.spring.henallux.ecommerce.Service;

import com.spring.henallux.ecommerce.Model.Cart;
import com.spring.henallux.ecommerce.Model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private HttpSession session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateTotalWithDiscount() {
        Cart cart = new Cart();
        Product product1 = new Product();
        product1.setProductId(1);
        product1.setPrice(100.0);
        Product product2 = new Product();
        product2.setProductId(2);
        product2.setPrice(50.0);
        Product product3 = new Product();
        product3.setProductId(3);
        product3.setPrice(25.0);

        cart.addItem(product1, 1);
        cart.addItem(product2, 1);
        cart.addItem(product3, 1);

        double total = cartService.calculateTotalWithDiscount(cart);

        // Le total sans remise serait 175.0
        // Avec 10% de remise, ça devrait être 157.5
        assertEquals(157.5, total, 0.01);
    }

    @Test
    void testCalculateTotalWithoutDiscount() {
        Cart cart = new Cart();
        Product product1 = new Product();
        product1.setProductId(1);
        product1.setPrice(100.0);

        cart.addItem(product1, 1);

        double total = cartService.calculateTotalWithDiscount(cart);

        // Pas de remise car il n'y a qu'un seul article
        assertEquals(100.0, total, 0.01);
    }



    @Test
    void testAddToCart() {
        Cart cart = new Cart();
        Product product = new Product();
        product.setProductId(1);
        product.setPrice(100.0);

        cartService.addToCart(cart, product, 2);

        assertEquals(1, cart.getItems().size());
        assertEquals(2, cart.getItems().get(1).getQuantity());
    }

    @Test
    void testRemoveFromCart() {
        Cart cart = new Cart();
        Product product = new Product();
        product.setProductId(1);
        product.setPrice(100.0);

        cartService.addToCart(cart, product, 2);
        cartService.removeFromCart(cart, 1);

        assertTrue(cart.getItems().isEmpty());
    }
}