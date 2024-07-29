package com.spring.henallux.ecommerce.Controller;

import com.spring.henallux.ecommerce.Model.Cart;
import com.spring.henallux.ecommerce.Model.CartLine;
import com.spring.henallux.ecommerce.Model.Product;
import com.spring.henallux.ecommerce.DataAccess.dao.ProductDataAccess;
import com.spring.henallux.ecommerce.Service.Constants;
import com.spring.henallux.ecommerce.Service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;

@Controller
@RequestMapping(value = "/cart")
@SessionAttributes({Constants.CURRENT_CART})
public class CartController {

    private ProductDataAccess productDAO;
    private PromotionService promotionService;

    @Autowired
    public CartController(ProductDataAccess productDAO, PromotionService promotionService) {
        this.productDAO = productDAO;
        this.promotionService = promotionService;
    }

    @ModelAttribute(Constants.CURRENT_CART)
    public Cart cart() {
        return new Cart();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String cart(@ModelAttribute(value = Constants.CURRENT_CART) Cart cart, Locale locale, Model model) {
        model.addAttribute("locale", locale);
        model.addAttribute("cart", cart);
        model.addAttribute("isEmpty", cart.getCartLines().isEmpty());
        return "integrated:cart";
    }

    @RequestMapping(value = "/editQuantity", method = RequestMethod.POST)
    public ResponseEntity<?> editQuantity(@ModelAttribute(value = Constants.CURRENT_CART) Cart cart, @RequestParam int productId, @RequestParam int quantity, Locale locale) {

        Product product = productDAO.findById(productId);
        HashMap<String, Object> response = new HashMap<>();
        if (product == null) {
            response.put("error", locale == Locale.FRENCH ? "Produit non trouvé" : "Product not found");
            return ResponseEntity.ok(response);
        }

        if (quantity < 1) {
            cart.removeProduct(productId);
        } else {
            if (product.getStock() < quantity) {
                response.put("error", locale == Locale.FRENCH ? "Pas assez de stock" : "Not enough stock");
                response.put("maxQuantity", product.getStock());
                return ResponseEntity.ok(response);
            }
            cart.editQuantity(productId, quantity);
        }
        // return response with new total price and total price with shipping cost
        response.put("success", true);
        response.put("totalPrice", cart.getTotalPrice());
        response.put("totalPriceWithShippingCost", cart.getTotalPriceWithShippingCost());

        // return response
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestParam int productId, @RequestParam int quantity, Locale locale,
                                        @ModelAttribute(value = Constants.CURRENT_CART) Cart cart) {
        Product product = productDAO.findById(productId);

        product = promotionService.applyPromotion(product);

        HashMap<String, Object> response = new HashMap<>();

        if (product == null) {
            response.put("error", locale == Locale.FRENCH ? "Produit non trouvé" : "Product not found");
            return ResponseEntity.ok(response);
        }

        int quantityTotal = quantity;
        // check if user alradey has this product in his cart and add quantity to it
        if (cart.getCartLines().containsKey(productId)) {
            quantityTotal += cart.getCartLines().get(productId).getQuantity();

        }

        if (product.getStock() < quantityTotal) {
            response.put("error", locale == Locale.FRENCH ? "Pas assez de stock" : "Not enough stock");
        } else {
            CartLine cartLine = new CartLine();
            cartLine.setProduct(product);
            cartLine.setQuantity(quantity);
            cart.addProduct(cartLine);
            response.put("success", locale == Locale.FRENCH ? "Produit ajouté au panier" : "Product added to cart");
        }


        return ResponseEntity.ok(response);
    }

}
