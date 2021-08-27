package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.*;

public class GetHTML {

    private static final Logger logger = LogManager.getLogger(GetHTML.class);

    public static Document downloadJsoup(String urlString, String fileName) throws IOException {
        Document document = Jsoup.connect(urlString).get(); //получили HTML страничку
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(document.outerHtml()); //Записали её в файл
        logger.info("HTML page saved in " + fileName);
        writer.close();
        return document;
    }

}
