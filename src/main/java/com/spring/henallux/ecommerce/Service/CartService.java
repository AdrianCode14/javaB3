package com.spring.henallux.ecommerce.Service;

import com.spring.henallux.ecommerce.Model.Cart;
import com.spring.henallux.ecommerce.Model.Product;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
public class CartService {

    public static final double DISCOUNT_THRESHOLD = 2;
    public static final double DISCOUNT_RATE = 0.1; // 10% discount

    public Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    public double calculateTotalWithDiscount(Cart cart) {
        double total = cart.calculateTotal();
        if (cart.getItemCount() >= DISCOUNT_THRESHOLD) {
            total *= (1 - DISCOUNT_RATE);
        }
        return BigDecimal.valueOf(total)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public void addToCart(Cart cart, Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        cart.addItem(product, quantity);
    }

    public void removeFromCart(Cart cart, int productId) {
        cart.removeItem(productId);
    }

    public void updateQuantity(Cart cart, int productId, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (quantity == 0) {
            removeFromCart(cart, productId);
        } else {
            cart.updateQuantity(productId, quantity);
        }
    }

}