package com.letstalkdata.iscream.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {
    private Integer id;
    private Flavor flavor;
    private int scoops;
    private List<Topping> toppings = new ArrayList<>();
    private BigDecimal totalPrice;

    public Order() {
    }

    public Order(Flavor flavor, int scoops, Topping... toppings) {
        this.flavor = flavor;
        this.scoops = scoops;
        this.toppings = Arrays.asList(toppings);
        this.totalPrice = calculatePrice();
    }

    private BigDecimal calculatePrice() {
        BigDecimal iceCreamCost = flavor.getUnitPrice()
                .multiply(BigDecimal.valueOf(scoops));
        BigDecimal toppingCost = toppings.stream()
                .map(Topping::getUnitPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return iceCreamCost.add(toppingCost);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Flavor getFlavor() {
        return flavor;
    }

    public void setFlavor(Flavor flavor) {
        this.flavor = flavor;
    }

    public int getScoops() {
        return scoops;
    }

    public void setScoops(int scoops) {
        this.scoops = scoops;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public BigDecimal getTotalPrice() {
        if(totalPrice == null){
            totalPrice = calculatePrice();
        }
        return totalPrice;
    }
}
