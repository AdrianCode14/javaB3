package com.spring.henallux.ecommerce.Service;

import com.spring.henallux.ecommerce.DataAccess.dao.OrderDataAccess;
import com.spring.henallux.ecommerce.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderDataAccess orderDAO;

    @Mock
    private CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrderFromCart() {
        // Arrange
        Cart cart = new Cart();
        User user = new User();
        user.setUserId(1);

        Product product1 = new Product();
        product1.setProductId(1);
        product1.setPrice(100.0);

        Product product2 = new Product();
        product2.setProductId(2);
        product2.setPrice(50.0);

        cart.addItem(product1, 2);
        cart.addItem(product2, 1);

        when(cartService.calculateTotalWithDiscount(cart)).thenReturn(225.0); // (100 * 2 + 50) * 0.9

        Order expectedOrder = new Order();
        expectedOrder.setOrderId(1);
        expectedOrder.setUser(user);
        expectedOrder.setTotalPrice(225.0);
        expectedOrder.setOrderStatus("PENDING");

        when(orderDAO.createOrderFromCart(cart, user)).thenReturn(expectedOrder);

        // Act
        Order result = orderService.createOrderFromCart(cart, user);

        // Assert
        assertNotNull(result);
        assertEquals(expectedOrder.getOrderId(), result.getOrderId());
        assertEquals(expectedOrder.getUser(), result.getUser());
        assertEquals(expectedOrder.getTotalPrice(), result.getTotalPrice());
        assertEquals(expectedOrder.getOrderStatus(), result.getOrderStatus());

        verify(orderDAO, times(1)).createOrderFromCart(cart, user);
        verify(cartService, times(1)).calculateTotalWithDiscount(cart);
    }

    @Test
    void testGetOrderById() {
        // Arrange
        Integer orderId = 1;
        Order expectedOrder = new Order();
        expectedOrder.setOrderId(orderId);
        when(orderDAO.getOrderById(orderId)).thenReturn(Optional.of(expectedOrder));

        // Act
        Optional<Order> result = orderService.getOrderById(orderId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedOrder.getOrderId(), result.get().getOrderId());
        verify(orderDAO, times(1)).getOrderById(orderId);
    }

    @Test
    void testUpdateOrderStatus() {
        // Arrange
        Integer orderId = 1;
        String newStatus = "SHIPPED";
        Order updatedOrder = new Order();
        updatedOrder.setOrderId(orderId);
        updatedOrder.setOrderStatus(newStatus);
        when(orderDAO.updateOrderStatus(orderId, newStatus)).thenReturn(updatedOrder);

        // Act
        Order result = orderService.updateOrderStatus(orderId, newStatus);

        // Assert
        assertNotNull(result);
        assertEquals(orderId, result.getOrderId());
        assertEquals(newStatus, result.getOrderStatus());
        verify(orderDAO, times(1)).updateOrderStatus(orderId, newStatus);
    }
}