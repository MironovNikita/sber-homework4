package org.example.task2_urlChecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlReader {
    public static String acceptUrl() {
        String url;
        BufferedReader bufferedReader;
        do {
            System.out.print("Введите URL ресурса: ");
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                url = bufferedReader.readLine();
            } catch (IOException exception) {
                url = "";
            }
        } while (url == null || url.isBlank());

        return url;
    }

    public static void readContent(String url) {
        try {
            URL website = new URL(url);
            URLConnection connection = website.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();

            System.out.println("\nСодержимое сайта извлечено");

        } catch (IOException exception) {
            System.out.println("Введён некорректный URL. Ошибка: " + exception.getMessage());
            readContent(acceptUrl());
        }

    }
}