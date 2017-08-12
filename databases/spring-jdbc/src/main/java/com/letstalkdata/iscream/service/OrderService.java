package com.letstalkdata.iscream.service;

import com.letstalkdata.iscream.domain.Ingredient;
import com.letstalkdata.iscream.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;

@Service
public class OrderService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void save(Order order) {
        int orderId = saveOrder(order);
        saveLineItem(orderId, order.getFlavor(), order.getScoops());
        order.getToppings()
                .forEach(topping -> saveLineItem(orderId, topping, 1));
    }

    private final static String CREATE_ORDER =
            "insert into purchase(total_price) values (?)";

    private int saveOrder(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = con -> {
            PreparedStatement ps = con.prepareStatement(CREATE_ORDER,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, order.getTotalPrice());
            return ps;
        };

        jdbcTemplate.update(psc, keyHolder);

        return keyHolder.getKey().intValue();
    }

    private final static String CREATE_ORDER_LINE_ITEM =
            "insert into purchase_line_item" +
                    "(purchase_id, ingredient_id, units) values(?, ?, ?)";

    private void saveLineItem(int orderId, Ingredient ingredient, int units) {
        jdbcTemplate.update(CREATE_ORDER_LINE_ITEM,
                orderId, ingredient.getId(), units);
    }

}
