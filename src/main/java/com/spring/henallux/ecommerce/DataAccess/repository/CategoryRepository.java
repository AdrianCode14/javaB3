package com.spring.henallux.ecommerce.DataAccess.repository;

import com.spring.henallux.ecommerce.DataAccess.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
    ArrayList<CategoryEntity> findAll();
    CategoryEntity findByLabelEn(String labelEn);
}
