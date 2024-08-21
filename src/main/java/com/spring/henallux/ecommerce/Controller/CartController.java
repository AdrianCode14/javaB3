package com.spring.henallux.ecommerce.Controller;

import com.spring.henallux.ecommerce.Model.Cart;
import com.spring.henallux.ecommerce.Model.Product;
import com.spring.henallux.ecommerce.Service.CartService;
import com.spring.henallux.ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @ModelAttribute("cart")
    public Cart cart(HttpSession session) {
        return cartService.getCart(session);
    }

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        Cart cart = cartService.getCart(session);
        double totalWithDiscount = cartService.calculateTotalWithDiscount(cart);
        model.addAttribute("cart", cart);
        model.addAttribute("totalWithDiscount", totalWithDiscount);
        return "integrated:cartView"; // Nom de la vue JSP
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") int productId,
                            @RequestParam("quantity") int quantity,
                            HttpSession session) {
        Cart cart = cartService.getCart(session);
        Product product = productService.findProductById(productId);
        cartService.addToCart(cart, product, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam("productId") int productId,
                                 HttpSession session) {
        Cart cart = cartService.getCart(session);
        cartService.removeFromCart(cart, productId);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateQuantity(@RequestParam("productId") int productId,
                                 @RequestParam("quantity") int quantity,
                                 HttpSession session) {
        Cart cart = cartService.getCart(session);
        cartService.updateQuantity(cart, productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/confirm")
    public String confirmCart(HttpSession session, Model model) {
        Cart cart = cartService.getCart(session);
        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/cart";
        }

        // Passez le panier à la page de confirmation
        model.addAttribute("cart", cart);
        return "confirmation"; // Nom de la vue JSP de confirmation
    }

    @PostMapping("/placeOrder")
    public String placeOrder(HttpSession session) {
        Cart cart = cartService.getCart(session);
        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/cart";
        }

        // Enregistrez la commande dans la base de données
        // cartService.saveOrder(cart);

        // Videz le panier
        session.removeAttribute("cart");

        return "redirect:/order/success"; // Redirige vers une page de succès après la commande
    }
}
