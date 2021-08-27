package com;

import com.dao.ParsingDataDAO;
import com.dao.impl.ParsingDataDAOImpl;
import com.model.ParsingData;
import com.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetText {

    private static final Logger logger = LogManager.getLogger(GetText.class);

    public static void parse(Document htmlPage) {
        logger.info("Starting parsing the received data");
        // С помощью Pattern и Matcher разбиваем полученный текст на отдельные слова
        Pattern pattern = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(htmlPage.text());
        List<String> words = new ArrayList<>(); //Список куда будут добавляться слова

        while (matcher.find()){
            words.add(matcher.group().toUpperCase());
        }

        // Считаем сколько раз каждое значение встречается в коллекции
        Map<String, Integer> unsortedMap = new HashMap<>();
        for (String x : words) {
            int newValue = unsortedMap.getOrDefault(x, 0) + 1;
            unsortedMap.put(x, newValue);
        }

        Map<String, Integer> sortedMap = sortByValue(unsortedMap);
        printMap(sortedMap);
        logger.info("Received data processed successfully");

        long now = System.currentTimeMillis();
        java.sql.Date sqlDate = new java.sql.Date(now);

        // Записываем в БД следующие данные {URL сайта, Дата, необработанный текст, результат парсинга}
        ParsingData parsingData = new ParsingData(htmlPage.location(), sqlDate, htmlPage.text(), sortedMap.toString());
        ParsingDataDAO parsingDataDAO = new ParsingDataDAOImpl();
        parsingDataDAO.save(parsingData);
        HibernateUtil.shutdown();
        logger.info("Parsing result saved in database successfully");
    }

    // Метод для вывода в консоль значений из коллекции
    public static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortedMap) {
        // 1. Конвертируем Map в List, потому что метод Collections.sort() принимает на вход только List
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortedMap.entrySet());

        // 2. Сортируем коллекцию по убыванию
        Collections.sort(list, new Comparator<>() {
            public int compare(Map.Entry<String, Integer> o2,
                               Map.Entry<String, Integer> o1) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // 3. Отсортированный лист помещаем в LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

}
