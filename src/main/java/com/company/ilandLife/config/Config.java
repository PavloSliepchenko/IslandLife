package com.company.ilandLife.config;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public abstract class Config {
    public static Map<String, Map<String, Double>> read(String path)  {
        List<String> rows = new ArrayList<>();

        try (Scanner scanner = new Scanner(Path.of(path))) {
            while (scanner.hasNext()) {
                rows.add(scanner.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[][] array = listToArray(rows);

        return arrayToMap(array);
    }

    private static String[][] listToArray(List<String> rows) {
        String[][] array = new String[rows.size()][rows.get(0).split(",").length];
        for(int i = 0; i < rows.size(); i++) {
            array[i] = rows.get(i).split(",");
        }
        return array;
    }

    private static Map<String, Map<String, Double>> arrayToMap (String[][] array) {
        Map<String, Map<String, Double>> result = new HashMap<>();

        for (int i = 1; i < array.length; i++) {
            Map<String, Double> tempMap = new HashMap<>();
            for (int j = 1; j < array[i].length; j++){
                tempMap.put(array[0][j], Double.parseDouble(array[i][j]));
            }
            result.put(array[i][0], tempMap);
        }

        return result;
    }
}
