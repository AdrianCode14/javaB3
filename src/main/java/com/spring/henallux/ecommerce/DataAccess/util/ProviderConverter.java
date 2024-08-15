package com.spring.henallux.ecommerce.DataAccess.util;
import org.springframework.security.core.GrantedAuthority;
import java.util.stream.Collectors;
import com.spring.henallux.ecommerce.Model.*;
import com.spring.henallux.ecommerce.DataAccess.entity.*;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProviderConverter {

    private <T, U> U convert(T source, Function<T, U> converter) {
        return source != null ? converter.apply(source) : null;
    }

    public User userEntityToUser(UserEntity userEntity) {
        return convert(userEntity, entity -> {
            User user = new User();
            user.setUserId(entity.getUserId());
            user.setEmail(entity.getEmail());
            user.setPassword(entity.getPassword());
            user.setFirstName(entity.getFirstName());
            user.setLastName(entity.getLastName());
            user.setDeliveryAddress(entity.getDeliveryAddress());
            user.setPhoneNumber(entity.getPhoneNumber());
            user.setAuthorities(entity.getAuthorities());
            user.setAccountNonExpired(entity.isAccountNonExpired());
            user.setAccountNonLocked(entity.isAccountNonLocked());
            user.setCredentialsNonExpired(entity.isCredentialsNonExpired());
            user.setEnabled(entity.isEnabled());
            return user;
        });
    }

    public UserEntity userToUserEntity(User user) {
        return convert(user, model -> {
            UserEntity entity = new UserEntity();
            entity.setUserId(model.getUserId());
            entity.setEmail(model.getEmail());
            entity.setPassword(model.getPassword());
            entity.setFirstName(model.getFirstName());
            entity.setLastName(model.getLastName());
            entity.setDeliveryAddress(model.getDeliveryAddress());
            entity.setPhoneNumber(model.getPhoneNumber());
            entity.setAuthorities(model.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(",")));
            entity.setAccountNonExpired(model.isAccountNonExpired());
            entity.setAccountNonLocked(model.isAccountNonLocked());
            entity.setCredentialsNonExpired(model.isCredentialsNonExpired());
            entity.setEnabled(model.isEnabled());
            return entity;
        });
    }

    public Product productEntityToProduct(ProductEntity productEntity) {
        return convert(productEntity, entity -> {
            Product product = new Product();
            product.setProductId(entity.getProductId());
            product.setLabelEn(entity.getLabelEn());
            product.setLabelFr(entity.getLabelFr());
            product.setDescriptionEn(entity.getDescriptionEn());
            product.setDescriptionFr(entity.getDescriptionFr());
            product.setPrice(entity.getPrice());
            product.setStock(entity.getStock());
            product.setCategory(categoryEntityToCategory(entity.getCategory()));
            return product;
        });
    }

    public ProductEntity productToProductEntity(Product product) {
        return convert(product, model -> {
            ProductEntity entity = new ProductEntity();
            entity.setProductId(model.getProductId());
            entity.setLabelEn(model.getLabelEn());
            entity.setLabelFr(model.getLabelFr());
            entity.setDescriptionEn(model.getDescriptionEn());
            entity.setDescriptionFr(model.getDescriptionFr());
            entity.setPrice(model.getPrice());
            entity.setStock(model.getStock());
            entity.setCategory(categoryToCategoryEntity(model.getCategory()));
            return entity;
        });
    }

    public Category categoryEntityToCategory(CategoryEntity categoryEntity) {
        return convert(categoryEntity, entity -> {
            Category category = new Category();
            category.setCategoryId(entity.getCategoryId());
            category.setLabelEn(entity.getLabelEn());
            category.setLabelFr(entity.getLabelFr());
            return category;
        });
    }

    public CategoryEntity categoryToCategoryEntity(Category category) {
        return convert(category, model -> {
            CategoryEntity entity = new CategoryEntity();
            entity.setCategoryId(model.getCategoryId());
            entity.setLabelEn(model.getLabelEn());
            entity.setLabelFr(model.getLabelFr());
            return entity;
        });
    }
}