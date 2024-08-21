package com.spring.henallux.ecommerce.Service;

import com.spring.henallux.ecommerce.Model.Cart;
import com.spring.henallux.ecommerce.Model.CartItem;
import com.spring.henallux.ecommerce.Model.Product;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

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
            return total * (1 - DISCOUNT_RATE);
        }
        return total;
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

    public Map<Integer, CartItem> getCartItems(Cart cart) {
        return cart.getItems();
    }

    public double getTotalPrice(Cart cart) {
        return calculateTotalWithDiscount(cart);
    }


}