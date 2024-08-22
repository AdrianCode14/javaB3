package com.spring.henallux.ecommerce.Controller;

import com.spring.henallux.ecommerce.Model.CartLine;
import com.spring.henallux.ecommerce.Model.Product;
import com.spring.henallux.ecommerce.DataAccess.dao.ProductDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Controller
public class ProductController {

    private ProductDataAccess productDAO;

    @Autowired
    public ProductController(ProductDataAccess productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping("/product/{productName}-{productId}")
    public String product(@PathVariable String productName, @PathVariable Integer productId, Model model, Locale locale) {
        Product product = productDAO.findByLabelEnAndId(productName, productId)
                .orElse(null);

        if (product == null)
            return "redirect:/error";

        List<Product> similarProducts = productDAO.findByCategory(product.getCategory());

        int finalProductId = product.getProductId();
        similarProducts.removeIf(p -> p.getProductId() == finalProductId);

        model.addAttribute("similarProducts", similarProducts);
        model.addAttribute("product", product);
        model.addAttribute("locale", locale);
        CartLine cartLine = new CartLine();
        cartLine.setQuantity(1);
        model.addAttribute("cartLine", cartLine);

        return "integrated:product";
    }
}