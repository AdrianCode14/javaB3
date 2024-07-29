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
@RequestMapping(value = "/")
public class HomeController {
    private ProductDataAccess productDAO;

    public HomeController(ProductDataAccess productDAO) {
        this.productDAO = productDAO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "integrated:home";
    }
}