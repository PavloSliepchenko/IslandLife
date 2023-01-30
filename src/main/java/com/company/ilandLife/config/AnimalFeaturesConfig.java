package com.company.ilandLife.config;

import java.util.*;

public class AnimalFeaturesConfig {
    private static final String PATH = "/Applications/IslandLife/src/main/resources/animalFeatures.csv";
    private static Map<String, Map<String, Double>> animalConfigMap = new HashMap<>();

    public static double getParameter(String animal, String parameter) {
        if (animalConfigMap.isEmpty()) {
            animalConfigMap = ConfigUtils.read(PATH);
        }
        System.out.println();
        return animalConfigMap.get(animal).get(parameter);
    }
}
