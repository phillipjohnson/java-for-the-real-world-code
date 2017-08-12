package com.letstalkdata.iscream.domain;

import java.math.BigDecimal;

public abstract class Ingredient {
    private Integer id;
    private String name;
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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
