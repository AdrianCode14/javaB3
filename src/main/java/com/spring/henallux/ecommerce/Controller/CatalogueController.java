package com.spring.henallux.ecommerce.Controller;

import com.spring.henallux.ecommerce.DataAccess.dao.ProductDataAccess;
import com.spring.henallux.ecommerce.Model.Category;
import com.spring.henallux.ecommerce.Model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class CatalogueController {
    private ProductDataAccess productDAO;

    public CatalogueController(ProductDataAccess productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping("/catalogue")
    public String displayCatalogue(@RequestParam(value = "category", required = false) Category category, Model model) {
        ArrayList<Product> products;

        if (category != null) {
            products = productDAO.findByCategory(category);
        } else {
            products = productDAO.findAll(); // Suppose que vous avez une méthode findAll() dans ProductDataAccess
        }

        model.addAttribute("products", products);
        return "integrated:catalogue";  // Le nom de votre fichier JSP sans extension
    }
}