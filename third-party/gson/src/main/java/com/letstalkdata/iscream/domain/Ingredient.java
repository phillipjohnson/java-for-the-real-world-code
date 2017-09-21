package com.letstalkdata.iscream.domain;

import java.math.BigDecimal;

public abstract class Ingredient {
    private Integer id;
    private String name;
    private BigDecimal unitPrice;

    public Ingredient(Integer id, String name, BigDecimal unitPrice) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
}
