package com.letstalkdata.iscream.service;


import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Phillip Johnson
 * Date: 4/28/17
 */
public class DailySpecialServiceTest {

    private static final String TEST_JSON_ROOT =
            "src/test/resources/json-samples";

    private String readJsonFromFile(String fileName) throws Exception {
        var jsonPath = Paths.get(TEST_JSON_ROOT, fileName);
        return new String(Files.readAllBytes(jsonPath));
    }

    @Test
    public void GivenZeroSpecials_EmptyListIsReturned() throws Exception {
        var json = readJsonFromFile("no_specials.json");
        var service = new DailySpecialService();
        var parsedFlavors = service.parseFlavorsFromJson(json);
        assertTrue(parsedFlavors.isEmpty());
    }

    @Test
    public void GivenThreeSpecials_ThenThreeFlavorsReturned() throws Exception {
        var json = readJsonFromFile("three_specials.json");
        var service = new DailySpecialService();
        var parsedFlavors = service.parseFlavorsFromJson(json);
        assertEquals(3, parsedFlavors.size());
    }

    @Test
    public void GivenOneSpecial_FlavorNameIsExtracted() throws Exception {
        var json = readJsonFromFile("one_special.json");
        var service = new DailySpecialService();
        var parsedFlavors = service.parseFlavorsFromJson(json);
        assertThat(parsedFlavors.get(0), is(equalTo("Salty Caramel")));
    }
}
