package com.letstalkdata.ui;

import com.letstalkdata.domain.Flavor;
import com.letstalkdata.domain.Order;
import com.letstalkdata.domain.Topping;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.text.NumberFormat;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created by Phillip Johnson on 8/31/2018
 */
@HtmlImport("frontend://styles/shared-styles.html")
@Route("")
public class OrderScreen extends Div {

    private OrderDetailsForm orderDetailsForm;
    private OrderSavedLayout orderSavedLayout;

    public OrderScreen() {
        orderDetailsForm = new OrderDetailsForm();
        orderSavedLayout = new OrderSavedLayout();
        add(orderDetailsForm);
        add(orderSavedLayout);

    }

    private class OrderDetailsForm extends FormLayout {
        private ListBox<Flavor> flavor;
        private TextField scoops;
        private List<Checkbox> toppings;
        private Button submit;

        public OrderDetailsForm() {
            flavor = new ListBox<>();
            flavor.setId("flavor");
            flavor.setItems(EnumSet.allOf(Flavor.class));

            addFormItem(flavor, "Flavor");
            add(new HtmlComponent("br"));

            scoops = new TextField("Scoops");
            add(scoops);
            add(new HtmlComponent("br"));

            Div toppingsDiv = new Div();
            toppingsDiv.setId("toppings-div");
            addFormItem(toppingsDiv, "Toppings");
            add(new HtmlComponent("br"));

            toppings = EnumSet.allOf(Topping.class)
                    .stream().map(topping -> new Checkbox(topping.toString()))
                    .peek(toppingsDiv::add)
                    .collect(Collectors.toList());


            submit = new Button("Submit");
            submit.setClassName("primary");
            submit.addClickListener(click -> {
                var order = new Order(
                        flavor.getValue().toString(),
                        Integer.parseInt(scoops.getValue()),
                        determineToppings());
                orderSavedLayout.showDetails(order);
            });

            add(submit);

        }

        Topping[] determineToppings() {
            return toppings.stream()
                    .filter(AbstractField::getValue)
                    .map(checkbox ->
                            Topping.valueOf(checkbox.getLabel().toUpperCase()))
                    .toArray(Topping[]::new);
        }
    }

    private static class OrderSavedLayout extends VerticalLayout {
        Text orderDetails;

        OrderSavedLayout() {
            setVisible(false);
            setMargin(false);
            orderDetails = new Text("");
            add(orderDetails);
        }

        void showDetails(Order order) {
            setVisible(true);
            var priceNumber = order.getPrice();
            var price = NumberFormat.getCurrencyInstance(Locale.US)
                    .format(priceNumber);
            orderDetails.setText("Order Saved! Total: " + price);
        }
    }
}
