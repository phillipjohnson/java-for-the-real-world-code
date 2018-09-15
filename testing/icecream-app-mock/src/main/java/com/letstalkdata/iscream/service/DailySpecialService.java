package com.letstalkdata.iscream.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DailySpecialService {

    private final static String SPECIALS_URL =
            "http://www.mocky.io/v2/590401621000003d034f66dc";

    public List<String> getSpecials() {
        try {
            var json = getJsonFromUrl();
            return parseFlavorsFromJson(json);
        } catch (IOException e) {
            System.out.println("Error retrieving daily specials!");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Note: This is static only to discuss mocking static methods
    public static boolean isServiceAvailable() throws IOException {
        var url = new URL(SPECIALS_URL);
        var con = (HttpURLConnection) url.openConnection();
        var is = con.getInputStream();
        return is.read() != -1;
    }

    private String getJsonFromUrl() throws IOException {
        var url = new URL(SPECIALS_URL);
        var con = (HttpURLConnection) url.openConnection();

        try(var in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            var response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        }
    }

    List<String> parseFlavorsFromJson(String json) {
        // For demonstration purposes only !
        // In real code, use a JSON parsing library.
        final var REGEX_PATTERN = "\"flavor\":\"(?<theFlavor>[\\w ]+)\"";
        var flavorRegex = Pattern.compile(REGEX_PATTERN);
        var matcher = flavorRegex.matcher(json);
        var flavors = new ArrayList<String>();
        while(matcher.find()) {
            flavors.add(matcher.group("theFlavor"));
        }
        return flavors;
    }
}
