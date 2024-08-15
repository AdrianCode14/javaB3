package com.spring.henallux.ecommerce.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@NoArgsConstructor
@Getter
@Setter
public class FilterForm {
    private String orderby;
    private Boolean isPromotion;

    @Min(value = 0, message = "{minprice}")
    private Double minPrice;

    @Max(value = 100000, message = "{maxprice}")
    private Double maxPrice;
}