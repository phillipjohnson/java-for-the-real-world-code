package com.letstalkdata.iscream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.
        AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(
                        Application.class,
                        ApplicationConfig.class);

        OrderMaker maker = ctx.getBean(OrderMaker.class);

        maker.makeRandomOrder();
        maker.makeBadOrder();
        maker.makeRandomOrder();
        maker.makeRandomOrder();
        maker.makeRandomOrder();
    }

}