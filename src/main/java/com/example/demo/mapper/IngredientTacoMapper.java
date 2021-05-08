package com.example.demo.mapper;

import com.example.demo.Ingredient;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IngredientTacoMapper {
    @Insert("insert into Taco_Ingredients(taco,ingredient) values (#{tacoId},#{ingredientId})")
    void saveIngredientToTaco(@Param("ingredientId") String ingredientId, @Param("tacoId") long tacoId);
}
