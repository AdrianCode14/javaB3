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

@Service
public class ProductDAO implements ProductDataAccess {

    private ProductRepository productRepository;
    private ProviderConverter providerConverter;

    @Autowired
    public ProductDAO(ProductRepository productRepository, ProviderConverter providerConverter) {
        this.productRepository = productRepository;
        this.providerConverter = providerConverter;
    }

    public Product findByLabelEnAndId(String labelEn, int id) {
        ProductEntity productEntity = productRepository.findByLabelEnAndId(labelEn, id);

        if (productEntity == null) {
            return null;
        }

        Product product = providerConverter.productEntityToProduct(productEntity);
        if (productEntity.getPromotionId() != null)
            product.setPromotion(providerConverter.promotionEntityToPromotion(productEntity.getPromotionId()));

        return product;
    }

    public Product findById(int id) {
        ProductEntity productEntity = productRepository.findById(id);

        if (productEntity == null) {
            return null;
        }

        Product product = providerConverter.productEntityToProduct(productEntity);
        if (productEntity.getPromotionId() != null)
            product.setPromotion(providerConverter.promotionEntityToPromotion(productEntity.getPromotionId()));

        return product;
    }

    public ArrayList<Product> findByCategory(Category category) {
        CategoryEntity categoryEntity = providerConverter.categoryToCategoryEntity(category);
        ArrayList<ProductEntity> productEntity = productRepository.findAllByCategoryId(categoryEntity);

        ArrayList<Product> products = new ArrayList<>();

        for (ProductEntity product : productEntity) {
            Product productToAdd = providerConverter.productEntityToProduct(product);
            if (product.getPromotionId() != null)
                productToAdd.setPromotion(providerConverter.promotionEntityToPromotion(product.getPromotionId()));
            products.add(productToAdd);


        }

        return products;
    }

    public ArrayList<Product> findAll() {
        ArrayList<ProductEntity> productEntity = productRepository.findAll();

        ArrayList<Product> products = new ArrayList<>();

        for (ProductEntity product : productEntity) {
            Product productToAdd = providerConverter.productEntityToProduct(product);
            if (product.getPromotionId() != null)
                productToAdd.setPromotion(providerConverter.promotionEntityToPromotion(product.getPromotionId()));
            products.add(productToAdd);
        }

        return products;
    }

    public Product save(Product product) {
        ProductEntity productEntity = providerConverter.productToProductEntity(product);
        productEntity = productRepository.save(productEntity);
        return providerConverter.productEntityToProduct(productEntity);
    }
}
