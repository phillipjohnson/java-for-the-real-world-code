package com.letstalkdata.iscream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    private static final String CONTEXT_PATH
            = "applicationContext.xml";

    public static void main(String[] args) {

        ApplicationContext ctx =
                new ClassPathXmlApplicationContext(CONTEXT_PATH);
        DailySpecialPrinter printer = ctx.getBean(DailySpecialPrinter.class);

        System.out.println("Starting store!\n\n==============\n");
        printer.printSpecials();
    }
}
