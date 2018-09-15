package com.letstalkdata.iscream.service;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.*;

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

    @DataProvider(name = "counts")
    public Object[][] createCounts() {
        return new Object[][] {
                {"no_specials", 0},
                {"one_special", 1},
                {"three_specials", 3}
        };
    }

    @Test(dataProvider = "counts")
    public void verifyCounts(String fileName, int expected) throws Exception {
        var json = readJsonFromFile(fileName + ".json");
        var service = new DailySpecialService();
        var parsedFlavors = service.parseFlavorsFromJson(json);
        assertEquals(parsedFlavors.size(), expected);
    }

    @Test
    public void GivenOneSpecial_FlavorNameIsExtracted() throws Exception {
        var json = readJsonFromFile("one_special.json");
        var service = new DailySpecialService();
        var parsedFlavors = service.parseFlavorsFromJson(json);
        assertEquals(parsedFlavors.get(0), "Salty Caramel");
    }
}