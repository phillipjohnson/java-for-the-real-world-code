package com.letstalkdata.iscream.controller;

import com.letstalkdata.iscream.domain.Flavor;
import com.letstalkdata.iscream.domain.Order;
import com.letstalkdata.iscream.domain.Topping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.NumberFormat;
import java.util.EnumSet;
import java.util.Locale;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String orderForm(Model model) {
        model.addAttribute("flavors",
                EnumSet.allOf(Flavor.class));
        model.addAttribute("toppings",
                EnumSet.allOf(Topping.class));
        return "new-order";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createOrder(@ModelAttribute Order order, Model model) {
        double priceNumber = order.getPrice();
        String price = NumberFormat.getCurrencyInstance(Locale.US)
                .format(priceNumber);
        model.addAttribute("price", price);
        return "order-success";
    }

}
