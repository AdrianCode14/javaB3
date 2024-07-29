package com.spring.henallux.ecommerce.DataAccess.dao;

import com.spring.henallux.ecommerce.Model.Category;

import java.util.ArrayList;

public interface CategoryDataAccess {
    ArrayList<Category> getAllCategories();
    Category findByLabelEn(String labelEn);
}
