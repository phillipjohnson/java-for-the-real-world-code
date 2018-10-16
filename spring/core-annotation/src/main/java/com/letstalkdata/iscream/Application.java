package com.letstalkdata.iscream;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Application {

    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(Application.class);
        var printer = ctx.getBean(DailySpecialPrinter.class);

        System.out.println("Starting store!\n\n==============\n");
        printer.printSpecials();
    }
}
