package com.letstalkdata.iscream;

import com.letstalkdata.iscream.service.DailySpecialService;

public class Application {
    public static void main(String[] args) {
        System.out.println("Starting store!\n\n==============\n");

        DailySpecialService service = new DailySpecialService();
        MenuCreator menuCreator = new MenuCreator(service);

        System.out.println(menuCreator.getTodaysMenu());
    }
}
