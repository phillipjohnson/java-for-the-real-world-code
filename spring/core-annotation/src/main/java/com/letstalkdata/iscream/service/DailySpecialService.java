package com.letstalkdata.iscream.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Author: Phillip Johnson
 * Date: 6/10/17
 */
@Service
public class DailySpecialService {
    private final String SPECIALS_URL =
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
        final String REGEX_PATTERN = "\"flavor\":\"(?<theFlavor>[\\w ]+)\"";
        var flavorRegex = Pattern.compile(REGEX_PATTERN);
        var matcher = flavorRegex.matcher(json);
        var flavors = new ArrayList<String>();
        while(matcher.find()) {
            flavors.add(matcher.group("theFlavor"));
        }
        return flavors;
    }
}
