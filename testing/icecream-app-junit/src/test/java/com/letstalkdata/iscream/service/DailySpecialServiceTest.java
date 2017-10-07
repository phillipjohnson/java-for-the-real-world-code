package com.letstalkdata.iscream.service;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Author: Phillip Johnson
 * Date: 4/28/17
 */
public class DailySpecialServiceTest {

    private static final String TEST_JSON_ROOT =
            "src/test/resources/json-samples";

    private String readJsonFromFile(String fileName) throws Exception {
        Path jsonPath = Paths.get(TEST_JSON_ROOT, fileName);
        return new String(Files.readAllBytes(jsonPath));
    }

    @Test
    public void GivenZeroSpecials_EmptyListIsReturned() throws Exception {
        String json = readJsonFromFile("no_specials.json");
        DailySpecialService service = new DailySpecialService();
        List<String> parsedFlavors = service.parseFlavorsFromJson(json);
        assertTrue(parsedFlavors.isEmpty());
    }

    @Test
    public void GivenThreeSpecials_ThenThreeFlavorsReturned() throws Exception {
        String json = readJsonFromFile("three_specials.json");
        DailySpecialService service = new DailySpecialService();
        List<String> parsedFlavors = service.parseFlavorsFromJson(json);
        assertEquals(3, parsedFlavors.size());
    }

    @Test
    public void GivenOneSpecial_FlavorNameIsExtracted() throws Exception {
        String json = readJsonFromFile("one_special.json");
        DailySpecialService service = new DailySpecialService();
        List<String> parsedFlavors = service.parseFlavorsFromJson(json);
        assertThat(parsedFlavors.get(0), is(equalTo("Salty Caramel")));
    }
}
