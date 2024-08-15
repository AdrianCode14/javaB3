package com.spring.henallux.ecommerce.Configuration;

import com.spring.henallux.ecommerce.DataAccess.dao.CategoryDataAccess;
import com.spring.henallux.ecommerce.Model.FilterForm;
import com.spring.henallux.ecommerce.Model.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomLocaleChangeInterceptor extends HandlerInterceptorAdapter {

    private final CategoryDataAccess categoryDAO;

    @Autowired
    public CustomLocaleChangeInterceptor(CategoryDataAccess categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (modelAndView != null) {
            modelAndView.addObject("categories", categoryDAO.getAllCategories());
            modelAndView.addObject("searchForm", new SearchForm());
            modelAndView.addObject("filterForm", new FilterForm());
        }
    }
}