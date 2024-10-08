package com.app;

import java.util.Scanner;

public class HttpImageStatusCli {

    public void askStatus() {
        try (var scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter HTTP status code, or type exit to quit the program: ");
                String input = scanner.next();
                if (input.equals("exit")) {
                    System.out.println("Goodbye!");
                    break;
                } else if (!input.matches("\\d+")) {
                    System.out.println("Please enter valid number");
                    continue;
                }
                int code = Integer.parseInt(input);
                var httpImageDownloader = new HttpStatusImageDownloader();

                try {
                    httpImageDownloader.downloadStatusImage(code);
                } catch (IllegalArgumentException e) {
                    System.out.println("There is no image for status code: " + code);
                }
            }
        }
    }
}
