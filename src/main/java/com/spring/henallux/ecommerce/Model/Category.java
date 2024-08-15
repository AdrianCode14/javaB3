package com.spring.henallux.ecommerce.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Integer categoryId;
    private String labelEn;
    private String labelFr;

    public String getName(Locale currentLocale) {
        return currentLocale.getLanguage().equals("fr") ? labelFr : labelEn;
    }
}