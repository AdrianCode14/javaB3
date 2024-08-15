package com.spring.henallux.ecommerce.Controller;

import com.spring.henallux.ecommerce.Model.Cart;
import com.spring.henallux.ecommerce.Model.Product;
import com.spring.henallux.ecommerce.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("cart")
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @ModelAttribute("cart")
    public Cart cart() {
        return new Cart();
    }

    @GetMapping
    public String viewCart(@ModelAttribute("cart") Cart cart, Model model) {
        model.addAttribute("totalWithDiscount", cartService.calculateTotalWithDiscount(cart));
        return "integrated:cart";
    }

    @PostMapping("/add")
    public String addToCart(@ModelAttribute("cart") Cart cart,
                            @RequestParam("productId") int productId,
                            @RequestParam("quantity") int quantity,
                            @RequestParam("price") double price,
                            @RequestParam("labelEn") String labelEn,
                            @RequestParam("labelFr") String labelFr) {
        Product product = new Product();
        product.setId(productId);
        product.setPrice(price);
        product.setLabelEn(labelEn);
        product.setLabelFr(labelFr);
        cartService.addToCart(cart, product, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@ModelAttribute("cart") Cart cart,
                                 @RequestParam("productId") int productId) {
        cartService.removeFromCart(cart, productId);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateQuantity(@ModelAttribute("cart") Cart cart,
                                 @RequestParam("productId") int productId,
                                 @RequestParam("quantity") int quantity) {
        cartService.updateQuantity(cart, productId, quantity);
        return "redirect:/cart";
    }
}