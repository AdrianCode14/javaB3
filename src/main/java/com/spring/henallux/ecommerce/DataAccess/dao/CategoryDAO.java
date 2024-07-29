package com.spring.henallux.ecommerce.DataAccess.dao;

import com.spring.henallux.ecommerce.Model.Category;
import com.spring.henallux.ecommerce.DataAccess.entity.CategoryEntity;
import com.spring.henallux.ecommerce.DataAccess.repository.CategoryRepository;
import com.spring.henallux.ecommerce.DataAccess.util.ProviderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryDAO implements CategoryDataAccess {

    private CategoryRepository categoryRepository;
    private ProviderConverter providerConverter;

    @Autowired
    public CategoryDAO(CategoryRepository categoryRepository, ProviderConverter providerConverter) {
        this.categoryRepository = categoryRepository;
        this.providerConverter = providerConverter;
    }

    @Override
    public ArrayList<Category> getAllCategories() {
        ArrayList<CategoryEntity> categoryEntities = categoryRepository.findAll();

        ArrayList<Category> categories = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryEntities) {
            categories.add(providerConverter.categoryEntityToCategory(categoryEntity));
        }

        return categories;
    }

    @Override
    public Category findByLabelEn(String labelEn) {
        CategoryEntity categoryEntity = categoryRepository.findByLabelEn(labelEn);

        if (categoryEntity == null) {
            return null;
        }

        return providerConverter.categoryEntityToCategory(categoryEntity);
    }
}
