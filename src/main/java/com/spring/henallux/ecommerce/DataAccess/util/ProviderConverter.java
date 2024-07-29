package com.spring.henallux.ecommerce.DataAccess.util;

import com.spring.henallux.ecommerce.Model.*;
import com.spring.henallux.ecommerce.DataAccess.entity.*;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

@Component
public class ProviderConverter {

    private DozerBeanMapper mapper = new DozerBeanMapper();

    public User userEntityToUser(UserEntity userEntity) {
        User user = new User();

        mapper.map(userEntity, user);
        user.setAuthorities(userEntity.getAuthorities());
        user.setAccountNonExpired(userEntity.getAccountNonExpired());
        user.setAccountNonLocked(userEntity.getAccountNonLocked());
        user.setCredentialsNonExpired(userEntity.getCredentialsNonExpired());
        user.setEnabled(userEntity.getEnabled());

        return user;
    }

    public UserEntity userToUserEntity(User user) {
        UserEntity userEntity = new UserEntity();


        mapper.map(user, userEntity);
        userEntity.setAuthorities(user.getAuthoritiesString());
        userEntity.setAccountNonExpired(user.getAccountNonExpired());
        userEntity.setAccountNonLocked(user.getAccountNonLocked());
        userEntity.setCredentialsNonExpired(user.getCredentialsNonExpired());
        userEntity.setEnabled(user.getEnabled());

        return userEntity;
    }

    public Product productEntityToProduct(ProductEntity productEntity) {
        return mapper.map(productEntity, Product.class);
    }

    public ProductEntity productToProductEntity(Product product) {
        return mapper.map(product, ProductEntity.class);
    }

    private Category getCategoryByCategoryEntity(CategoryEntity categoryEntity) {
        Category category = new Category();
        category.setId(categoryEntity.getId());
        category.setLabelEn(categoryEntity.getLabelEn());
        category.setLabelFr(categoryEntity.getLabelFr());
        return category;
    }

    public Promotion promotionEntityToPromotion(PromotionEntity promotionEntity) {
        return mapper.map(promotionEntity, Promotion.class);
    }

    public OrderEntity orderToOrderEntity(Order order) {
        return mapper.map(order, OrderEntity.class);
    }

    public OrderLineEntity orderLineToOrderLineEntity(OrderLine orderLine) {
        OrderLineEntity orderLineEntity = new OrderLineEntity();
        orderLineEntity.setQuantity(orderLine.getQuantity());
        orderLineEntity.setPrice(orderLine.getPrice());
        return orderLineEntity;
    }

    public Order orderEntityToOrder(OrderEntity orderEntity) {
        return mapper.map(orderEntity, Order.class);
    }

    public Category categoryEntityToCategory(CategoryEntity categoryEntity) {
        return mapper.map(categoryEntity, Category.class);
    }

    public CategoryEntity categoryToCategoryEntity(Category category) {
        return mapper.map(category, CategoryEntity.class);
    }

    public OrderLine orderLineEntityToOrderLine(OrderLineEntity orderLineEntity) {
        return mapper.map(orderLineEntity, OrderLine.class);
    }
}
