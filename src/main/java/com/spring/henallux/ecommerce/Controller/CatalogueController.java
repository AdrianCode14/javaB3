package com.spring.henallux.ecommerce.Controller;

import com.spring.henallux.ecommerce.DataAccess.dao.ProductDataAccess;
import com.spring.henallux.ecommerce.DataAccess.dao.CategoryDataAccess;
import com.spring.henallux.ecommerce.Model.Category;
import com.spring.henallux.ecommerce.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/catalogue")
public class CatalogueController {
    private ProductDataAccess productDAO;
    private CategoryDataAccess categoryDAO;

    @Autowired
    public CatalogueController(ProductDataAccess productDAO, CategoryDataAccess categoryDAO) {
        this.productDAO = productDAO;
        this.categoryDAO = categoryDAO;
    }

    @GetMapping
    public String displayCatalogue(@RequestParam(value = "category", required = false) String categoryLabel, Model model, Locale locale) {
        List<Product> products;
        List<Category> categories = categoryDAO.getAllCategories();

        if (categoryLabel != null && !categoryLabel.isEmpty()) {
            Category category = categoryDAO.findByLabelEn(categoryLabel);
            if (category != null) {
                products = productDAO.findByCategory(category);
            } else {
                products = productDAO.findAll();
            }
        } else {
            products = productDAO.findAll();
        }

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", categoryLabel);
        model.addAttribute("locale", locale);
        return "integrated:catalogue";
    }

    @GetMapping("/product/{productId}")
    public String displayProductDetails(@PathVariable("productId") int productId, Model model, Locale locale) {
        Product product = productDAO.findById(productId); // Supposant que tu as une méthode findById dans ton DAO
        if (product == null) {
            return "redirect:/catalogue"; // Redirige vers le catalogue si le produit n'existe pas
        }
        model.addAttribute("product", product);
        model.addAttribute("locale", locale);
        return "integrated:productDetails"; // La vue JSP pour les détails du produit
    }
}