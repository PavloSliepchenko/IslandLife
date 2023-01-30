package com.company.ilandLife.config;

import java.util.*;

public class EatingPossibilityConfig {
    private static final String PATH = "/Applications/IslandLife/src/main/resources/possibilityToBeEaten.csv";
    private static Map<String, Map<String, Double>> configMap = new HashMap<>();

    public static double eatingPossibility(String predator, String victim) {
        if(configMap.isEmpty()) {
            configMap = ConfigUtils.read(PATH);
        }
        return configMap.get(predator).get(victim);
    }
}
