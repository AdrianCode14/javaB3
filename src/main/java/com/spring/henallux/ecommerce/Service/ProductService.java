package com.spring.henallux.ecommerce.Service;

import com.spring.henallux.ecommerce.DataAccess.dao.ProductDataAccess;
import com.spring.henallux.ecommerce.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductService {

    private final ProductDataAccess productDataAccess;

    @Autowired
    public ProductService(ProductDataAccess productDataAccess) {
        this.productDataAccess = productDataAccess;
    }

    public Product findProductById(Integer productId) {
        return productDataAccess.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
    }
}