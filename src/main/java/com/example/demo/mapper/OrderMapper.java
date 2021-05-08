package com.example.demo.mapper;

import com.example.demo.Ingredient;
import com.example.demo.Order;
import com.example.demo.Taco;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderMapper {
    @Insert("insert into Taco_Order(deliveryName,deliveryStreet,deliveryCity,deliveryState,deliveryZip" +
            ",ccNumber,ccExpiration,ccCVV,placedAt) values (#{deliveryName}," +
            "#{deliveryStreet},#{deliveryCity},#{deliveryState}"+
            ",#{deliveryZip},#{ccNumber},#{ccExpiration},#{ccCVV},#{placedAt})")
    @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class,
            before=false, statement="select last_insert_id()")
    long saveOrderDetails(Order order);

}

