package com.letstalkdata.iscream.service;

import com.google.common.base.Splitter;
import java.util.List;

public class DailySpecialService {

    public List<String> getSpecials() {
        var specialsRawText = "|Salty Caramel|Coconut Chip|Maui Mango";
        return Splitter.on('|')
          .omitEmptyStrings()
          .splitToList(specialsRawText);
    }
}
