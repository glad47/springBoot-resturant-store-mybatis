package com.example.demo.mapper;

import com.example.demo.Ingredient;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface IngredientMapper {
    @Select("SELECT * FROM INGREDIENT")
    List<Ingredient> findAll();

    @Select("select * from Ingredient where id=#{id}")
    Ingredient findOne(@Param("id") String id);

    @Insert("insert into Ingredient(id,name,type) values(#{id},#{name},#{type})")
    Ingredient save(Ingredient ingredient);
}
