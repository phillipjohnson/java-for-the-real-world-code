package com.letstalkdata.iscream.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "purchase_line_item")
public class OrderLineItem {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    @JsonIgnore
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "units")
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

    @JsonIgnore
    public BigDecimal getLineItemCost() {
        return ingredient == null || units == null
                ? BigDecimal.ZERO
                : ingredient.getUnitPrice().multiply(BigDecimal.valueOf(units));
    }
}
