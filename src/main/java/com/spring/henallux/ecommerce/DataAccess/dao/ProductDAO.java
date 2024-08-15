package com.spring.henallux.ecommerce.DataAccess.dao;

import com.spring.henallux.ecommerce.Model.Category;
import com.spring.henallux.ecommerce.Model.Product;
import com.spring.henallux.ecommerce.DataAccess.entity.CategoryEntity;
import com.spring.henallux.ecommerce.DataAccess.entity.ProductEntity;
import com.spring.henallux.ecommerce.DataAccess.repository.ProductRepository;
import com.spring.henallux.ecommerce.DataAccess.util.ProviderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class ProductDAO implements ProductDataAccess {

    private ProductRepository productRepository;
    private ProviderConverter providerConverter;

    @Autowired
    public ProductDAO(ProductRepository productRepository, ProviderConverter providerConverter) {
        this.productRepository = productRepository;
        this.providerConverter = providerConverter;
    }

    @Override
    public Product findById(int id) {
        ProductEntity productEntity = productRepository.findById(id);
        return productEntity != null ? providerConverter.productEntityToProduct(productEntity) : null;
    }

    @Override
    public Product findByLabelEnAndId(String labelEn, int id) {
        ProductEntity productEntity = productRepository.findByLabelEnAndProductId(labelEn, id);
        return productEntity != null ? providerConverter.productEntityToProduct(productEntity) : null;
    }

    @Override
    public ArrayList<Product> findByCategory(Category category) {
        CategoryEntity categoryEntity = providerConverter.categoryToCategoryEntity(category);
        ArrayList<ProductEntity> productEntities = productRepository.findAllByCategory(categoryEntity);
        return productEntities.stream()
                .map(providerConverter::productEntityToProduct)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    @Override
    public ArrayList<Product> findAll() {
        ArrayList<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream()
                .map(providerConverter::productEntityToProduct)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}