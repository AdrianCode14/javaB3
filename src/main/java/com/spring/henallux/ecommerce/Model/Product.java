package com.spring.henallux.ecommerce.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String labelEn;
    private String labelFr;
    private String descriptionEn;
    private String descriptionFr;
    private Category categoryId;
    private Promotion promotion;
    private String dimension;
    private double weight;
    private double price;
    private int stock;
    private Date dateAdded;
    private double oldPrice;
    private Boolean isPromotion;

    public String getLocalizedLabel(Locale locale) {
        return "fr".equalsIgnoreCase(locale.getLanguage()) ? labelFr : labelEn;
    }

    public String getLocalizedDescription(Locale locale) {
        return "fr".equalsIgnoreCase(locale.getLanguage()) ? descriptionFr : descriptionEn;
    }

    public ArrayList<String> getImages() {
        ArrayList<String> images = new ArrayList<>();
        images.add(id + "-1");
        images.add(id + "-2");
        return images;
    }

    @Override
    public String toString() {
        // return all element
        return "Product{" +
                "id=" + id +
                ", labelEn='" + labelEn + '\'' +
                ", labelFr='" + labelFr + '\'' +
                ", descriptionEn='" + descriptionEn + '\'' +
                ", descriptionFr='" + descriptionFr + '\'' +
                ", category=" + categoryId +
                ", promotion=" + promotion +
                ", dimension='" + dimension + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", stock=" + stock +
                ", dateAdded='" + dateAdded + '\'' +
                '}';
    }
}
