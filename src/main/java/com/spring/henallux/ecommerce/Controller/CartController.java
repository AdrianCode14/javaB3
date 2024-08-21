package com.spring.henallux.ecommerce.Controller;

import com.spring.henallux.ecommerce.Model.Cart;
import com.spring.henallux.ecommerce.Model.Product;
import com.spring.henallux.ecommerce.Service.CartService;
import com.spring.henallux.ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")  // Ajoutez cette annotation
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService; // Ajoutez ce service pour obtenir les produits

    @ModelAttribute("cart")  // Ajoutez cette méthode
    public Cart cart(HttpSession session) {
        return cartService.getCart(session);
    }

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        Cart cart = cartService.getCart(session); // Obtenez le panier depuis la session
        double totalWithDiscount = cartService.calculateTotalWithDiscount(cart);
        model.addAttribute("cart", cart);
        model.addAttribute("totalWithDiscount", totalWithDiscount);
        return "integrated:cartView"; // Nom de la vue JSP
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") int productId,
                            @RequestParam("quantity") int quantity,
                            HttpSession session) {
        Cart cart = cartService.getCart(session); // Obtenez le panier depuis la session
        Product product = productService.findProductById(productId); // Obtenez le produit par ID
        cartService.addToCart(cart, product, quantity);
        return "redirect:/cart"; // Redirige vers la vue du panier
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam("productId") int productId,
                                 HttpSession session) {
        Cart cart = cartService.getCart(session); // Obtenez le panier depuis la session
        cartService.removeFromCart(cart, productId);
        return "redirect:/cart"; // Redirige vers la vue du panier
    }

    @PostMapping("/update")
    public String updateQuantity(@RequestParam("productId") int productId,
                                 @RequestParam("quantity") int quantity,
                                 HttpSession session) {
        Cart cart = cartService.getCart(session); // Obtenez le panier depuis la session
        cartService.updateQuantity(cart, productId, quantity);
        return "redirect:/cart"; // Redirige vers la vue du panier
    }

    @PostMapping("/confirm")
    public String confirmOrder(Model model, HttpSession session) {
        if (!isUserAuthenticated()) {
            return "redirect:/login";
        }
        Cart cart = cartService.getCart(session);
        model.addAttribute("cartItems", cart.getItems());
        model.addAttribute("totalPrice", cartService.calculateTotalWithDiscount(cart));
        return "integrated:confirmOrder";
    }

    private boolean isUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() &&
                !(authentication.getPrincipal() instanceof String);
    }
}
