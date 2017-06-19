package com.letstalkdata.iscream.controller;

import com.letstalkdata.iscream.service.DailySpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Author: Phillip Johnson
 * Date: 6/11/17
 */
@Controller
public class SpecialsController {

    private DailySpecialService dailySpecialService;

    @Autowired
    public SpecialsController(DailySpecialService dailySpecialService) {
        this.dailySpecialService = dailySpecialService;
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("specials", dailySpecialService.getSpecials());
        return "specials";
    }
}
