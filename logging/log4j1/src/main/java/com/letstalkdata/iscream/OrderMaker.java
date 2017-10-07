package com.letstalkdata.iscream;

import com.letstalkdata.iscream.domain.Ingredient;
import com.letstalkdata.iscream.domain.Order;
import com.letstalkdata.iscream.service.IngredientService;
import com.letstalkdata.iscream.service.OrderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMaker {

    private static final Logger log = LogManager.getLogger(OrderMaker.class);

    private IngredientService ingredientService;
    private OrderService orderService;

    @Autowired
    public OrderMaker(IngredientService ingredientSvc, OrderService orderSvc) {
        this.ingredientService = ingredientSvc;
        this.orderService = orderSvc;
    }

    public void makeRandomOrder() {
        List<Ingredient> flavors = ingredientService.getFlavors();
        List<Ingredient> toppings = ingredientService.getToppings();
        Ingredient myFlavor = getRandom(flavors, 1).get(0);
        List<Ingredient> myToppings = getRandom(toppings, 3);
        myToppings.add(myFlavor);

        Order order = new Order(toppings, 1);
        orderService.save(order);
        log.info(String.format("Saved Order ID %d!", order.getId()));
    }

    public void makeBadOrder() {
        try {
            Order order = new Order();
            orderService.save(order); // This line intentionally errors
        } catch (PersistenceException e) {
            log.error("Error saving order!", e);
        }
    }

    private <T> List<T> getRandom(List<T> all, int desired) {
        Collections.shuffle(all);
        return all.stream()
                .limit(desired)
                .collect(Collectors.toList());
    }
}
