package com.letstalkdata;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class Collections {

    @Test
    public void immutables() {
        List<String> myList = ImmutableList.of("blue", "green", "yellow");
        Set<String> mySet = ImmutableSet.of("blue", "green", "yellow");
    }

    @Test
    public void newCollections() {
        BiMap<String, String> usersToEmail = HashBiMap.create();
        usersToEmail.put("footballfan", "bill@example.com");
        usersToEmail.put("dragon", "sue@something.org");

        assert "dragon".equals(usersToEmail.inverse().get("sue@something.org"));

        Multiset<String> multi = HashMultiset.create();
        multi.add("a");
        multi.add("b");
        multi.add("b");
        multi.add("c");
        multi.add("c");
        multi.add("c");

        assert 3 == multi.count("c");

        Table<Integer, String, Double> data = HashBasedTable.create();
        data.put(1, "Abe Bondley", 68000.00d);
        data.put(2, "Helli Sivewright", 54000.00d);
        data.put(3, "Kevan Loughtan", 45000.00d);

        assert 45000.00d == data.row(3).get("Kevan Loughtan");
        assert 68000.00d == data.column("Abe Bondley").get(1);
    }
}
