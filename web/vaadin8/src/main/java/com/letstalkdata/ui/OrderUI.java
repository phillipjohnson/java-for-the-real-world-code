package com.letstalkdata.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

@Theme("mytheme")
public class OrderUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        OrderScreen orderScreen = new OrderScreen();
        setContent(orderScreen);
    }

    @WebServlet(urlPatterns = "/*",
            name = "OrderUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = OrderUI.class, productionMode = false)
    public static class OrderUIServlet extends VaadinServlet {
    }
}
