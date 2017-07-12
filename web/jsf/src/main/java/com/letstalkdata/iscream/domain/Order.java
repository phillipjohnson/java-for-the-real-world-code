package com.letstalkdata.iscream.domain;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@ManagedBean
public class Order {
    private String flavor;
    private int scoops = 1;
    private List<Topping> toppings = new ArrayList<>();

    @ManagedProperty("formattedPrice")
    private String formattedPrice;

    @ManagedProperty("saved")
    private boolean saved;

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

    public String getFormattedPrice() {
        return formattedPrice;
    }

    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    private double calculatePrice() {
        return scoops * 1.50d + toppings.size() * 0.25d;
    }

    public void save() {
        this.saved = true;
        formattedPrice = NumberFormat.getCurrencyInstance(Locale.US)
                .format(calculatePrice());
    }

}
