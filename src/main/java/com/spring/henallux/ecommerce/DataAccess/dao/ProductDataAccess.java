package com.spring.henallux.ecommerce.DataAccess.dao;

import com.spring.henallux.ecommerce.Model.Category;
import com.spring.henallux.ecommerce.Model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDataAccess {
    Optional<Product> findById(int id);
    Optional<Product> findByLabelEnAndId(String labelEn, int id);
    List<Product> findByCategory(Category category);
    List<Product> findAll();
}