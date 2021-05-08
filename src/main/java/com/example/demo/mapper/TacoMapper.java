package com.example.demo.mapper;

import com.example.demo.Ingredient;
import com.example.demo.Taco;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TacoMapper {

    @Insert("insert into Taco(name,createdAt) values (#{name},#{createdAt})")
    @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class,
            before=false, statement="select last_insert_id()")
    long saveTacoInfo(Taco taco);


}
