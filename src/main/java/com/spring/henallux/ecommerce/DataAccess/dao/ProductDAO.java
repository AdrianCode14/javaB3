package com.spring.henallux.ecommerce.DataAccess.dao;

import com.spring.henallux.ecommerce.Model.Category;
import com.spring.henallux.ecommerce.Model.Product;
import com.spring.henallux.ecommerce.DataAccess.entity.CategoryEntity;
import com.spring.henallux.ecommerce.DataAccess.entity.ProductEntity;
import com.spring.henallux.ecommerce.DataAccess.repository.ProductRepository;
import com.spring.henallux.ecommerce.DataAccess.util.ProviderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductDAO implements ProductDataAccess {

    private final ProductRepository productRepository;
    private final ProviderConverter providerConverter;

    @Autowired
    public ProductDAO(ProductRepository productRepository, ProviderConverter providerConverter) {
        this.productRepository = productRepository;
        this.providerConverter = providerConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(int id) {
        ProductEntity productEntity = productRepository.findById(id);
        return Optional.ofNullable(productEntity)
                .map(providerConverter::productEntityToProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findByLabelEnAndId(String labelEn, int id) {
        ProductEntity productEntity = productRepository.findByLabelEnAndProductId(labelEn, id);
        return Optional.ofNullable(productEntity)
                .map(providerConverter::productEntityToProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByCategory(Category category) {
        CategoryEntity categoryEntity = providerConverter.categoryToCategoryEntity(category);
        List<ProductEntity> productEntities = productRepository.findAllByCategory(categoryEntity);
        return productEntities.stream()
                .map(providerConverter::productEntityToProduct)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream()
                .map(providerConverter::productEntityToProduct)
                .collect(Collectors.toList());
    }
}