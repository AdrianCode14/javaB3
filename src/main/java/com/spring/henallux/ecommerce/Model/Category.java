package com.spring.henallux.ecommerce.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Locale;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Category {
    private int id;
    private String labelEn;
    private String labelFr;

    public String getName(Locale locale) {
        if (locale.getLanguage().equals("fr")) {
            return labelFr;
        }
        return labelEn;
    }

    public String getLink() {
        return "category/" + labelEn;
    }

    public String getLocalizedLabel(Locale locale) {
        return "fr".equalsIgnoreCase(locale.getLanguage()) ? labelFr : labelEn;
    }
}
