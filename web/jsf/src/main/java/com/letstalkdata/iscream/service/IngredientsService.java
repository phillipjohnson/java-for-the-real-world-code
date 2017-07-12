package com.letstalkdata.iscream.service;

import com.letstalkdata.iscream.domain.Flavor;
import com.letstalkdata.iscream.domain.Topping;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name = "ingredientsService")
@ApplicationScoped
public class IngredientsService {

    public List<String> getFlavors() {
        List<String> flavors = new ArrayList<>();
        flavors.add("");
        flavors.addAll(EnumSet.allOf(Flavor.class).stream()
                    .map(Flavor::toString)
                    .collect(Collectors.toList()));
        return flavors;
    }

    public List<String> getToppings() {
        return EnumSet.allOf(Topping.class).stream()
                .map(Topping::toString)
                .collect(Collectors.toList());
    }
}
