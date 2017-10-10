package com.letstalkdata.iscream;

import com.letstalkdata.iscream.service.DailySpecialService;

import java.util.List;

public class MenuCreator {

    private DailySpecialService dailySpecialService;

    public MenuCreator(DailySpecialService dailySpecialService) {
        this.dailySpecialService = dailySpecialService;
    }

    public String getTodaysMenu() {
        List<String> dailySpecials = dailySpecialService.getSpecials();

        StringBuilder menuBuilder = new StringBuilder("Today's specials are:\n");
        dailySpecials.forEach(s ->
                menuBuilder.append(" - ").append(s).append("\n"));

        return menuBuilder.toString();
    }

}
