package com.app;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;

public class HttpStatusImageDownloader {

    public void downloadStatusImage(int code) {
        try {
            HttpStatusChecker httpStatusChecker = new HttpStatusChecker();
            URL url = URI.create(httpStatusChecker.getStatusImage(code)).toURL();
            byte[] imageBytes = getAllBytesForImage(url.toString());
            fileWrite(imageBytes, "src/main/resources/" + code + ".jpg");
        } catch (IOException e) {
            System.out.println("Error while downloading image: " + e.getMessage());
        }
    }

    private byte[] getAllBytesForImage(String url) {
        try {
            URL imageUrl = URI.create(url).toURL();
            HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            return connection.getInputStream().readAllBytes();
        } catch (Exception e) {
            System.out.println("Error while getting all bytes for image: " + e.getMessage());
            return new byte[0];
        }

    }

    private void fileWrite(byte[] bytes, String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                Files.write(file.toPath(), bytes);
                System.out.println("File created successfully: " + file.getPath());
            } else {
                System.out.println("File already exists: " + file.getPath());
            }
        } catch (IOException e) {
            System.out.println("Error while writing to file: " + e.getMessage());

        }
    }
}
