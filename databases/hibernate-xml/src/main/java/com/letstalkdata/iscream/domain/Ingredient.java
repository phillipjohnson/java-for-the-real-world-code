package com.letstalkdata.iscream.domain;

import java.math.BigDecimal;

public class Ingredient {

    public enum Type {
        ICE_CREAM, TOPPING
    }

    private Integer id;
    private String name;
    private Type type;
    private BigDecimal unitPrice;

    public Ingredient() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
