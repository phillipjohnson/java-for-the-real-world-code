package com.letstalkdata.iscream.service;

import com.google.common.collect.Lists;
import java.util.List;

public class DailySpecialService {

    public List<String> getSpecials() {
        return Lists.newArrayList("Salty Caramel", "Coconut Chip", "Maui Mango");
    }
}
