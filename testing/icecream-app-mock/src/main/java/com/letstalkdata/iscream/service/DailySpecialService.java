package com.letstalkdata.iscream.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DailySpecialService {

    private final static String SPECIALS_URL =
            "http://www.mocky.io/v2/590401621000003d034f66dc";

    public List<String> getSpecials() {
        System.out.println("Retrieving specials from " + SPECIALS_URL);
        try {
            String json = getJsonFromUrl();
            return parseFlavorsFromJson(json);
        } catch (IOException e) {
            System.out.println("Error retrieving daily specials!");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Note: This is static only to discuss mocking static methods
    public static boolean isServiceAvailable() throws IOException {
        URL url = new URL(SPECIALS_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        InputStream is = con.getInputStream();
        return is.read() != -1;
    }

    private String getJsonFromUrl() throws IOException {
        URL url = new URL(SPECIALS_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        try(BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        }
    }

    List<String> parseFlavorsFromJson(String json) {
        // For demonstration purposes only !
        // In real code, use a JSON parsing library.
        final String REGEX_PATTERN = "\"flavor\":\"(?<theFlavor>[\\w ]+)\"";
        Pattern flavorRegex = Pattern.compile(REGEX_PATTERN);
        Matcher matcher = flavorRegex.matcher(json);
        List<String> flavors = new ArrayList<>();
        while(matcher.find()) {
            flavors.add(matcher.group("theFlavor"));
        }
        return flavors;
    }
}
