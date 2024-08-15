package com.spring.henallux.ecommerce.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer productId;
    private String labelEn;
    private String labelFr;
    private String descriptionEn;
    private String descriptionFr;
    private Double price;
    private Integer stock;
    private Category category;

    public String getLocalizedLabel(String language) {
        return "fr".equalsIgnoreCase(language) ? labelFr : labelEn;
    }

    public String getLocalizedDescription(String language) {
        return "fr".equalsIgnoreCase(language) ? descriptionFr : descriptionEn;
    }

    public void setId(int productId) {
        this.productId = productId;
    }
}