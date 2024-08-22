package com.spring.henallux.ecommerce.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Cart {
    private Map<Integer, CartItem> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    public void addItem(Product product, int quantity) {
        if (product.getProductId() == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        int productId = product.getProductId();
        CartItem item = items.get(productId);
        if (item == null) {
            items.put(productId, new CartItem(product, quantity));
        } else {
            item.setQuantity(item.getQuantity() + quantity);
        }
    }

    public void removeItem(int productId) {
        items.remove(productId);
    }

    public void updateQuantity(int productId, int quantity) {
        CartItem item = items.get(productId);
        if (item != null) {
            if (quantity > 0) {
                item.setQuantity(quantity);
            } else {
                items.remove(productId);
            }
        }
    }

    public double calculateTotal() {
        return items.values().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public int getItemCount() {
        return items.size();
    }
}
