package com.letstalkdata.iscream.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letstalkdata.iscream.domain.Ingredient;
import com.letstalkdata.iscream.domain.Order;
import com.letstalkdata.iscream.service.IngredientService;
import com.letstalkdata.iscream.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private IngredientService ingredientService;
    private OrderService orderService;

    @Autowired
    public OrderController(IngredientService ingredientService,
                           OrderService orderService) {
        this.ingredientService = ingredientService;
        this.orderService = orderService;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String orderForm(Model model) {
        model.addAttribute("flavors",
                ingredientService.getFlavors());
        model.addAttribute("toppings",
                ingredientService.getToppings());
        return "new-order";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createOrder(@ModelAttribute NewOrderRequest newOrderRequest,
                              Model model) {
        Ingredient flavor
                = ingredientService.getIngredientById(newOrderRequest.flavor);
        List<Ingredient> ingredients = Arrays.stream(newOrderRequest.toppings)
                .mapToObj(id -> ingredientService.getIngredientById(id))
                .collect(Collectors.toList());
        ingredients.add(flavor);
        Order order = new Order(ingredients, newOrderRequest.scoops);

        BigDecimal priceNumber = order.getTotalPrice();
        String price = NumberFormat.getCurrencyInstance(Locale.US)
                .format(priceNumber);
        model.addAttribute("price", price);

        orderService.save(order);

        return "order-success";
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @RequestMapping(value = "/fromJson", method = RequestMethod.POST,
            produces = "application/json")
    @ResponseBody
    public String createOrderFromJson(@RequestBody String orderJson)
            throws Exception {
        Order order = MAPPER.readValue(orderJson, Order.class);
        order.getOrderLineItems().forEach(li -> li.setOrder(order));
        orderService.save(order);

        return MAPPER.writeValueAsString(order);
    }

    @RequestMapping(value = "", method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public String all() throws Exception {
        List<Order> orders = orderService.getAllOrders();
        return MAPPER
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(orders);
    }

    private static class NewOrderRequest {
        private int flavor;
        private int scoops;
        private int[] toppings;

        public NewOrderRequest() {}

        public int getFlavor() {
            return flavor;
        }

        public void setFlavor(int flavor) {
            this.flavor = flavor;
        }

        public int getScoops() {
            return scoops;
        }

        public void setScoops(int scoops) {
            this.scoops = scoops;
        }

        public int[] getToppings() {
            return toppings;
        }

        public void setToppings(int[] toppings) {
            this.toppings = toppings;
        }
    }
}
