package com.letstalkdata.iscream.domain;

import java.math.BigDecimal;

public class OrderLineItem {

    private Integer id;
    private Order order;
    private Ingredient ingredient;
    private Integer units;

    public OrderLineItem() {

    }

    public OrderLineItem(Order order, Ingredient ingredient, Integer units) {
        this.order = order;
        this.ingredient = ingredient;
        this.units = units;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public BigDecimal getLineItemCost() {
        return ingredient == null || units == null
                ? BigDecimal.ZERO
                : ingredient.getUnitPrice().multiply(BigDecimal.valueOf(units));
    }
}
