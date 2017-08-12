package com.letstalkdata.iscream.service;

import com.letstalkdata.iscream.domain.Flavor;
import com.letstalkdata.iscream.domain.Topping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IngredientService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public IngredientService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Flavor> flavorRowMapper = (rs, rowNum) -> {
        Integer id = rs.getInt("id");
        String name = rs.getString("ingredient");
        BigDecimal unitPrice = rs.getBigDecimal("unit_price");
        return new Flavor(id, name, unitPrice);
    };

    public List<Flavor> getFlavors() {
        String sql = "select id, ingredient, unit_price from ingredient " +
                "where ingredient_type = 'ICE_CREAM'";
        return jdbcTemplate.query(sql, flavorRowMapper);
    }

    private static final String INGREDIENT_BY_ID =
            "select id, ingredient, unit_price from ingredient where id = ?";

    public Flavor getFlavorById(int id) {
        return jdbcTemplate.queryForObject(INGREDIENT_BY_ID,
                flavorRowMapper, id);
    }

    private RowMapper<Topping> toppingRowMapper = (rs, rowNum) -> {
        Integer id = rs.getInt("id");
        String name = rs.getString("ingredient");
        BigDecimal unitPrice = rs.getBigDecimal("unit_price");
        return new Topping(id, name, unitPrice);
    };

    public List<Topping> getToppings() {
        String sql = "select id, ingredient, unit_price from ingredient " +
                "where ingredient_type = 'TOPPING'";
        return jdbcTemplate.query(sql, toppingRowMapper);
    }

    public Topping getToppingById(int id) {
        return jdbcTemplate.queryForObject(INGREDIENT_BY_ID,
                toppingRowMapper, id);
    }


}
