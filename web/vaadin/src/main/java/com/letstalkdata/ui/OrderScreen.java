package com.letstalkdata.ui;

import com.letstalkdata.domain.Flavor;
import com.letstalkdata.domain.Order;
import com.letstalkdata.domain.Topping;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.text.NumberFormat;
import java.util.EnumSet;
import java.util.Locale;

public class OrderScreen extends VerticalLayout {

    private OrderDetailsForm orderDetailsForm;
    private OrderSavedLayout orderSavedLayout;

    public OrderScreen() {
        orderDetailsForm = new OrderDetailsForm();
        orderSavedLayout = new OrderSavedLayout();

        addComponent(orderDetailsForm);
        addComponent(orderSavedLayout);
    }

    private class OrderDetailsForm extends FormLayout {
        private NativeSelect<Flavor> flavor;
        private TextField scoops;
        private CheckBoxGroup<Topping> toppings;
        private Button submit;

        public OrderDetailsForm() {
            flavor = new NativeSelect<>(
                    "Flavor",
                    EnumSet.allOf(Flavor.class));
            scoops = new TextField("Scoops");
            toppings = new CheckBoxGroup<>(
                    "Toppings",
                    EnumSet.allOf(Topping.class));

            submit = new Button("Submit");
            submit.setStyleName(ValoTheme.BUTTON_PRIMARY);
            submit.addClickListener(click -> {
                Order order = new Order(
                        flavor.getValue().toString(),
                        Integer.parseInt(scoops.getValue()),
                        toppings.getValue().toArray(new Topping[]{}));
                orderSavedLayout.showDetails(order);
            });

            addComponent(flavor);
            addComponent(scoops);
            addComponent(toppings);
            addComponent(submit);
        }
    }

    private static class OrderSavedLayout extends VerticalLayout {
        Label orderDetails;

        OrderSavedLayout() {
            setVisible(false);
            setMargin(false);
            orderDetails = new Label();
            addComponent(orderDetails);
        }

        void showDetails(Order order) {
            setVisible(true);
            double priceNumber = order.getPrice();
            String price = NumberFormat.getCurrencyInstance(Locale.US)
                    .format(priceNumber);
            orderDetails.setValue("Order Saved! Total: " + price);
        }
    }
}
