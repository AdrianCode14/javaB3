package com.spring.henallux.ecommerce.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilterForm {
    String orderby;
    Boolean ispromotion;
    @Min(value = 0, message = "{minprice}")
    Double minprice;
    @Max(value = 100000, message = "{maxprice}")
    Double maxprice;

}
