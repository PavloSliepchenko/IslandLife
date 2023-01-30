package com.company.ilandLife.config;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class ConfigUtils {
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

    /**
     * the following method converts String[][] to Map<String, Map<String, Double>>
     *     it accepts arrays in the following format
     *                  param1  param2  param3  param4 ....
     *     animalName1   data    data    data    data  ....
     *     animalName2   data    data    data    data  ....
     *     animalName3   data    data    data    data  ....
     *     animalName4   data    data    data    data  ....
     *     animalName5   data    data    data    data  ....
     *     To create an inner map (Map<String, Double>), which is the value in the returned map,
     *     the first row is always used for the keys (array[0][j]). Thus for array[0][1] it's param1.
     *     As the keys in the returned map the first column is used (array[i][0]). Thus for array[3][0] it's animalName3
     * @param "String[][]"
     * @return Map<String, Map<String, Double>>
     */
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
