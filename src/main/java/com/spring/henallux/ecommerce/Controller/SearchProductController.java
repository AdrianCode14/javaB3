package com.spring.henallux.ecommerce.Controller;

import com.spring.henallux.ecommerce.Model.Category;
import com.spring.henallux.ecommerce.Model.Product;
import com.spring.henallux.ecommerce.DataAccess.dao.CategoryDataAccess;
import com.spring.henallux.ecommerce.DataAccess.dao.ProductDataAccess;
import com.spring.henallux.ecommerce.Service.PromotionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/search-product")
public class SearchProductController {
    private CategoryDataAccess categoryDAO;
    private ProductDataAccess productDAO;
    private PromotionService promotionService;

    public SearchProductController(CategoryDataAccess categoryDAO, ProductDataAccess productDAO, PromotionService promotionService) {
        this.categoryDAO = categoryDAO;
        this.productDAO = productDAO;
        this.promotionService = promotionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String searchProduct(
            @RequestParam(value = "categoryLabel", required = false) String categoryLabel,
            @RequestParam(value = "filter", required = false) String searchLabel,
            @RequestParam(value = "orderby", required = false) String orderBy,
            @RequestParam(value = "ispromotion", required = false) Boolean isPromotion,
            @RequestParam(value = "minprice", required = false) Double minPrice,
            @RequestParam(value = "maxprice", required = false) Double maxPrice,
            Model model, Locale locale) {
        model.addAttribute("locale", locale);
        ArrayList<Product> products;

        if (orderBy == null) {
            orderBy = "labelasc";
        }

        if (isPromotion == null) {
            isPromotion = false;
        }

        if (categoryLabel == null && searchLabel == null) {
            products = productDAO.findAll();

            products = promotionService.calculatePromotionOfProducts(products);

            if (isPromotion) {
                products = filterByPromotion(products);
            }

            products = filterByOrderBy(products, orderBy, locale);

            products = filterByPrice(products, minPrice, maxPrice);

            model.addAttribute("products", products);
            return "integrated:search-product";
        }
        Category category;
        if (categoryLabel == null) {
            categoryLabel = "all";
        }

        if (categoryLabel.toLowerCase().equals("all")) {
            products = productDAO.findAll();
            if (searchLabel != null) {
                products = filterBySearch(products, searchLabel, locale);
            }
            products = promotionService.calculatePromotionOfProducts(products);

            model.addAttribute("products", products);
            return "integrated:search-product";
        }

        category = categoryDAO.findByLabelEn(categoryLabel);
        products = productDAO.findByCategory(category);

        if (searchLabel != null) {
            products = filterBySearch(products, searchLabel, locale);
        }

        products = promotionService.calculatePromotionOfProducts(products);
        products = filterByOrderBy(products, orderBy, locale);
        products = filterByPrice(products, minPrice, maxPrice);

        model.addAttribute("products", products);
        return "integrated:search-product";
    }

    ArrayList<Product> filterBySearch(ArrayList<Product> products, String searchLabel, Locale locale) {
        if (locale.getLanguage().equals("fr")) {
            return products
                    .stream()
                    .filter(product -> product.getLabelFr().toLowerCase().contains(searchLabel.toLowerCase()))
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return products
                .stream()
                .filter(product -> product.getLabelEn().toLowerCase().contains(searchLabel.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));

    }

    ArrayList<Product> filterByPromotion(ArrayList<Product> products) {
        return products
                .stream()
                .filter(product -> product.getPromotion() != null && product.getPromotion().isPromotionValid())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    ArrayList<Product> filterByOrderBy(ArrayList<Product> products, String orderBy, Locale locale) {
        switch (orderBy) {
            case "priceasc":
                products.sort(Comparator.comparingDouble(Product::getPrice));
                break;
            case "pricedesc":
                products.sort((p1, p2) -> Double.compare(p2.getPrice(), (p1.getPrice())));
                break;
            case "labelasc":
                products.sort(Comparator.comparing(p -> p.getLocalizedLabel(locale)));
                break;
            case "labeldesc":
                products.sort((p1, p2) -> p2.getLocalizedLabel(locale).compareTo(p1.getLocalizedLabel(locale)));
                break;
        }
        return products;
    }

    ArrayList<Product> filterByPrice(ArrayList<Product> products, Double minPrice, Double maxPrice) {
        if (minPrice == null) {
            minPrice = 0.0;
        }
        if (maxPrice == null) {
            maxPrice = 100000.0;
        }
        Double finalMinPrice = minPrice;
        Double finalMaxPrice = maxPrice;
        return products
                .stream()
                .filter(product -> product.getPrice() >= finalMinPrice && product.getPrice() <= finalMaxPrice)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
