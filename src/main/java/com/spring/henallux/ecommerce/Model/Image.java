package com.spring.henallux.ecommerce.Model;

import com.spring.henallux.ecommerce.DataAccess.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Image {
    private int id;
    private ProductEntity productId;
    private byte[] image;
}
