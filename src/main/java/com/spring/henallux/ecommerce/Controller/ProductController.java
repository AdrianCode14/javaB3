package com.spring.henallux.ecommerce.Controller;

import com.spring.henallux.ecommerce.Model.CartLine;
import com.spring.henallux.ecommerce.Model.Product;
import com.spring.henallux.ecommerce.DataAccess.dao.ProductDataAccess;
import com.spring.henallux.ecommerce.Service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Locale;

@Controller
public class ProductController {

    private ProductDataAccess productDAO;
    private PromotionService promotionService;

    @Autowired
    public ProductController(ProductDataAccess productDAO, PromotionService promotionService) {
        this.productDAO = productDAO;
        this.promotionService = promotionService;
    }

    @GetMapping("/product/{productName}-{productId}")
    public String product(@PathVariable String productName, @PathVariable Integer productId, Model model, Locale locale) {
        Product product = productDAO.findByLabelEnAndId(productName, productId);

        product = promotionService.applyPromotion(product);

        ArrayList<Product> similarProducts = productDAO.findByCategory(product.getCategoryId());

        // Remove the current product from the list
        int finalProductId = product.getId();
        similarProducts.removeIf(p -> p.getId() == finalProductId);


        model.addAttribute("similarProducts", similarProducts);
        model.addAttribute("product", product);
        model.addAttribute("locale", locale);
        CartLine cartLine = new CartLine();
        cartLine.setQuantity(1);
        model.addAttribute("cartLine", cartLine);

        if (product == null)
            return "redirect:/error";

        return "integrated:product";
    }
}