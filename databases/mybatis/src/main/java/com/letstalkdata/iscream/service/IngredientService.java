package com.letstalkdata.iscream.service;

import com.letstalkdata.iscream.domain.Flavor;
import com.letstalkdata.iscream.domain.Topping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IngredientService {
    List<Flavor> getFlavors();

    Flavor getFlavorById(@Param("id") int id);

    List<Topping> getToppings();

    Topping getToppingById(@Param("id") int id);
}
