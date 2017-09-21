package com.letstalkdata.iscream.service;

import com.letstalkdata.iscream.domain.Flavor;
import com.letstalkdata.iscream.domain.Ingredient;
import com.letstalkdata.iscream.domain.Order;
import com.letstalkdata.iscream.domain.Topping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

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

    private final static String GET_ALL_ORDERS =
            "select p.id, li.units scoops, i.id as flavor_id, " +
                    "ingredient flavor, i.unit_price\n" +
                    "from purchase p\n" +
                    "    join purchase_line_item li on p.id = li.purchase_id\n"+
                    "    join ingredient i on li.ingredient_id = i.id\n" +
                    "where 1=1\n" +
                    "and i.ingredient_type = 'ICE_CREAM'";

    private final static String GET_TOPPINGS_FOR_ORDER =
            "select i.id, ingredient, unit_price\n"+
                    "from purchase p\n"+
                    "    join purchase_line_item li on p.id = li.purchase_id\n"+
                    "    join ingredient i on li.ingredient_id = i.id\n"+
                    "where 1=1\n"+
                    "and i.ingredient_type = 'TOPPING'\n"+
                    "and p.id = ?";

    public List<Order> getAllOrders() {
        List<Order> orders = jdbcTemplate.query(GET_ALL_ORDERS, orderRowMapper);
        orders.forEach(order -> order.setToppings(getToppingsForOrder(order)));
        return orders;
    }

    private List<Topping> getToppingsForOrder(Order order) {
        return jdbcTemplate.query(GET_TOPPINGS_FOR_ORDER,
                toppingRowMapper,
                order.getId());
    }

    private RowMapper<Order> orderRowMapper = (rs, rowNum) -> {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setScoops(rs.getInt("scoops"));
        Flavor flavor = new Flavor(
                rs.getInt("flavor_id"),
                rs.getString("flavor"),
                rs.getBigDecimal("unit_price"));
        order.setFlavor(flavor);

        return order;
    };

    private RowMapper<Topping> toppingRowMapper = (rs, rowNum) ->
            new Topping(rs.getInt("id"),
                    rs.getString("ingredient"),
                    rs.getBigDecimal("unit_price"));
}
