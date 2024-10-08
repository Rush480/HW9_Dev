package com.app;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;


public class HttpStatusChecker {

    private HashMap<String, String> statusList = new HashMap<>();
    private static final String CAT_URL = "https://http.cat/";


    public String getStatusImage(int code) {
        try {
            URL url = URI.create(CAT_URL + code).toURL();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (statusList.containsKey(String.valueOf(code))) {
                return statusList.get(String.valueOf(code));
            } else if (responseCode != HttpURLConnection.HTTP_NOT_FOUND) {
                statusList.put(String.valueOf(code), CAT_URL + code);
                return CAT_URL + code;
            } else {
                throw new IllegalArgumentException("HTTP Code: " + responseCode);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Error fetching image", e);
        }
    }
}
