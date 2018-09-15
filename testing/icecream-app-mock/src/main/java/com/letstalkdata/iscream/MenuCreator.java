package com.letstalkdata.iscream;

import com.letstalkdata.iscream.service.DailySpecialService;

public class MenuCreator {

    private DailySpecialService dailySpecialService;

    public MenuCreator(DailySpecialService dailySpecialService) {
        this.dailySpecialService = dailySpecialService;
    }

    public String getTodaysMenu() {
        var dailySpecials = dailySpecialService.getSpecials();

        var menuBuilder = new StringBuilder("Today's specials are:\n");
        dailySpecials.forEach(s ->
                menuBuilder.append(" - ").append(s).append("\n"));

        return menuBuilder.toString();
    }

}
