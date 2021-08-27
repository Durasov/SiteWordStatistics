package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        //String urlString = "https://jsoup.org/";
        try {
            logger.info("------------------------------------");
            logger.info("App started");

            Scanner in = new Scanner(System.in);
            System.out.print("Input a url: ");
            String url = in.nextLine();

            // Пока в консоли не будет введен адрес сайта, удовлетворяющий логику регулярного выражения будем просить ввести URL заново.
            while (!url.matches("(http://www\\.|https://|http://).+\\.(com|ru|рф|org)/.*")){
                System.out.println("Invalid site address format. Try again");
                System.out.print("Input a url: ");
                url = in.nextLine();
            }

            GetText.parse(GetHTML.downloadJsoup(url, "page.html"));

        } catch (IllegalArgumentException | UnknownHostException | FileNotFoundException | NullPointerException ex) {
            System.out.println("Error " + ex);
            logger.error("Error ", ex);
        }
    }
}
