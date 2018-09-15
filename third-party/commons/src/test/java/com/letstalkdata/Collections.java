package com.letstalkdata;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Collections {

    @Test
    public void bimap() {
        BidiMap<String, String> usersToEmail = new DualHashBidiMap<>();
        usersToEmail.put("footballfan", "bill@example.com");
        usersToEmail.put("dragon", "sue@something.org");

        assert "dragon".equals(usersToEmail.getKey("sue@something.org"));
    }

    @Test
    public void utils() {
        var ints = List.of(1, 2, 3);
        assert 3 == CollectionUtils.size(ints.iterator());

        var a = Set.of(1,2,3,4);
        var b = Set.of(1,2,4);
        SetUtils.SetView<Integer> result = SetUtils.difference(a, b);

        assert 1 == result.size();
        assert result.contains(3);
    }

}
