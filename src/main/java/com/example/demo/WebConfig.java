package com.example.demo;

import com.example.demo.mapper.IngredientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private IngredientMapper ingredientMapper;
    @Autowired
    public WebConfig(IngredientMapper ingredientMapper){
        this.ingredientMapper=ingredientMapper;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(new IngredientByIdConverter(ingredientMapper));
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");

    }

}
