package com.letstalkdata.iscream.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DailySpecialService {

    private String specialsUrl;

    public DailySpecialService(@Value("${specials.url}") String specialsUrl) {
        this.specialsUrl = specialsUrl;
    }

    public List<String> getSpecials() {
        try {
            String json = getJsonFromUrl();
            return parseFlavorsFromJson(json);
        } catch (IOException e) {
            System.out.println("Error retrieving daily specials!");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private String getJsonFromUrl() throws IOException {
        URL url = new URL(specialsUrl);
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
