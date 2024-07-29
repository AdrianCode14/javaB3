package com.spring.henallux.ecommerce.Controller;

import com.spring.henallux.ecommerce.DataAccess.dao.ProductDataAccess;
import com.spring.henallux.ecommerce.Model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/catalogue")
public class CatalogueController {
    private ProductDataAccess productDAO;

    public CatalogueController(ProductDataAccess productDAO) {
        this.productDAO = productDAO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model, Locale locale) {
        model.addAttribute("products", productDAO.findAll());
        model.addAttribute("locale", locale);
        return "integrated:catalogue";
    }
}