package com.spring.henallux.ecommerce.DataAccess.repository;

import com.spring.henallux.ecommerce.DataAccess.entity.CategoryEntity;
import com.spring.henallux.ecommerce.DataAccess.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    ProductEntity findById(int id);
    ProductEntity findByLabelEnAndProductId(String labelEn, int productId);
    ArrayList<ProductEntity> findAllByCategory(CategoryEntity categoryEntity);
    ArrayList<ProductEntity> findAll();
}