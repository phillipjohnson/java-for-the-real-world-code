package com.letstalkdata.iscream.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    public enum Type {
        ICE_CREAM, TOPPING
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ingredient")
    private String name;

    @Column(name = "ingredient_type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "unit_price")
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
