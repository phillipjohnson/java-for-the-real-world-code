package com.letstalkdata.iscream;

import com.letstalkdata.iscream.service.DailySpecialService;

public class Application {
    public static void main(String[] args) {
        System.out.println("Starting store!\n\n==============\n");

        var service = new DailySpecialService();
        var menuCreator = new MenuCreator(service);

        System.out.println(menuCreator.getTodaysMenu());
    }
}
