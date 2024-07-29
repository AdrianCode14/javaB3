package com.spring.henallux.ecommerce.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SearchForm {
    private String filter;
    private String categoryLabel;
    private String orderby;
}
