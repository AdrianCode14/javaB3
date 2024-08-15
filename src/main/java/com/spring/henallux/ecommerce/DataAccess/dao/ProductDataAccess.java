package com.spring.henallux.ecommerce.DataAccess.dao;

import com.spring.henallux.ecommerce.Model.Category;
import com.spring.henallux.ecommerce.Model.Product;

import java.util.ArrayList;

public interface ProductDataAccess {
    Product findById(int id);
    Product findByLabelEnAndId(String labelEn, int id);
    ArrayList<Product> findByCategory(Category category);
    ArrayList<Product> findAll();
}