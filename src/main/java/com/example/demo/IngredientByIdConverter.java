package com.example.demo;


import com.example.demo.mapper.IngredientMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientMapper ingredientMapper;

    @Autowired
    public IngredientByIdConverter(IngredientMapper ingredientMapper) {
        this.ingredientMapper = ingredientMapper;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientMapper.findOne(id);
    }



}