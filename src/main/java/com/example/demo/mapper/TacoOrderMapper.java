package com.example.demo.mapper;

import com.example.demo.Taco;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TacoOrderMapper {


    @Insert("insert into Taco_Order_Tacos(tacoOrder,taco) values (#{orderId},#{tacoId})")
    void saveTacoToOrder(@Param("tacoId") long tacoId, @Param("orderId") long orderId);
}
