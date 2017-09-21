package com.letstalkdata.iscream.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {
    private String flavor;
    private int scoops;
    private List<Topping> toppings = new ArrayList<>();

    public Order() {

    }

    public Order(String flavor, int scoops, Topping... toppings) {
        this.flavor = flavor;
        this.scoops = scoops;
        this.toppings = Arrays.asList(toppings);
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
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

    public double getPrice() {
        return scoops * 1.50d + toppings.size() * 0.25d;
    }
}
