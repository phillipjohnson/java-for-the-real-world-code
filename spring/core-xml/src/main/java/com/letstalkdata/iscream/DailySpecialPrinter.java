package com.letstalkdata.iscream;

import com.letstalkdata.iscream.service.DailySpecialService;

public class DailySpecialPrinter {

    private DailySpecialService dailySpecialService;

    public DailySpecialService getDailySpecialService() {
        return dailySpecialService;
    }

    public void setDailySpecialService(DailySpecialService dailySpecialService) {
        this.dailySpecialService = dailySpecialService;
    }

    public void printSpecials() {
        var dailySpecials = dailySpecialService.getSpecials();

        System.out.println("Today's specials are:");
        dailySpecials.forEach(s -> System.out.println(" - " + s));
    }
}
