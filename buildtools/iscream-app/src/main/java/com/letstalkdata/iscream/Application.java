package com.letstalkdata.iscream;

import com.letstalkdata.iscream.service.DailySpecialService;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        System.out.println("Starting store!\n\n==============\n");

        var dailySpecialService = new DailySpecialService();
        var dailySpecials = dailySpecialService.getSpecials();

        System.out.println("Today's specials are:");
        dailySpecials.forEach(s -> System.out.println(" - " + s));
    }
}
