package com.spring.henallux.ecommerce.DataAccess.util;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
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
            user.setAccountNonExpired(entity.getAccountNonExpired() != null ? entity.getAccountNonExpired() : true);
            user.setAccountNonLocked(entity.getAccountNonLocked() != null ? entity.getAccountNonLocked() : true);
            user.setCredentialsNonExpired(entity.getCredentialsNonExpired() != null ? entity.getCredentialsNonExpired() : true);
            user.setEnabled(entity.getEnabled() != null ? entity.getEnabled() : true);
            user.setNickname(entity.getNickname());
            user.setFavoriteColor(entity.getFavoriteColor());
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
            entity.setNickname(model.getNickname());
            entity.setFavoriteColor(model.getFavoriteColor());
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
    public Order orderEntityToOrder(OrderEntity orderEntity) {
        return convert(orderEntity, entity -> {
            Order order = new Order();
            order.setOrderId(entity.getOrderId());
            order.setDate(entity.getDate());
            order.setOrderStatus(entity.getOrderStatus());
            order.setUser(userEntityToUser(entity.getUser()));
            order.setTotalPrice(entity.getTotalPrice());
            if (entity.getOrderLines() != null) {
                order.setOrderLines(entity.getOrderLines().stream()
                        .map(this::orderLineEntityToOrderLine)
                        .collect(Collectors.toList()));
            } else {
                order.setOrderLines(new ArrayList<>());
            }
            return order;
        });
    }

    public OrderEntity orderToOrderEntity(Order order) {
        return convert(order, model -> {
            OrderEntity entity = new OrderEntity();
            entity.setOrderId(model.getOrderId());
            entity.setDate(model.getDate());
            entity.setOrderStatus(model.getOrderStatus());
            entity.setUser(userToUserEntity(model.getUser()));
            entity.setTotalPrice(model.getTotalPrice());
            entity.setOrderLines(model.getOrderLines().stream()
                    .map(this::orderLineToOrderLineEntity)
                    .collect(Collectors.toList()));
            return entity;
        });
    }

    public OrderLine orderLineEntityToOrderLine(OrderLineEntity orderLineEntity) {
        return convert(orderLineEntity, entity -> {
            OrderLine orderLine = new OrderLine();
            orderLine.setLineId(entity.getLineId());
            orderLine.setUnitPrice(entity.getUnitPrice());
            orderLine.setQuantity(entity.getQuantity());
            orderLine.setProduct(productEntityToProduct(entity.getProduct()));
            return orderLine;
        });
    }

    public OrderLineEntity orderLineToOrderLineEntity(OrderLine orderLine) {
        return convert(orderLine, model -> {
            OrderLineEntity entity = new OrderLineEntity();
            entity.setLineId(model.getLineId());
            entity.setUnitPrice(model.getUnitPrice());
            entity.setQuantity(model.getQuantity());
            entity.setProduct(productToProductEntity(model.getProduct()));
            return entity;
        });
    }
}