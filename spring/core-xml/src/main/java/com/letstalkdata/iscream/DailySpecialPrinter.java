package com.letstalkdata.iscream;

import com.letstalkdata.iscream.service.DailySpecialService;

import java.util.List;

public class DailySpecialPrinter {

    private DailySpecialService dailySpecialService;

    public DailySpecialService getDailySpecialService() {
        return dailySpecialService;
    }

    public void setDailySpecialService(DailySpecialService dailySpecialService) {
        this.dailySpecialService = dailySpecialService;
    }

    public void printSpecials() {
        List<String> dailySpecials = dailySpecialService.getSpecials();

        System.out.println("Today's specials are:");
        dailySpecials.forEach(s -> System.out.println(" - " + s));
    }
}
