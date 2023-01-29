package com.company.ilandLife.view;

import com.company.ilandLife.animal.Animal;
import com.company.ilandLife.island.Island;
import com.company.ilandLife.island.IslandCell;

import java.util.List;

public class View implements Runnable {
    private static final String BUFFALO = "\uD83D\uDC03";
    private static final String BEAR = "\uD83D\uDC3B";
    private static final String HORSE = "\uD83D\uDC0E";
    private static final String DEER = "\uD83E\uDD8C";
    private static final String BOAR = "\uD83D\uDC17";
    private static final String SHEEP = "\uD83D\uDC11";
    private static final String GOAT = "\uD83D\uDC10";
    private static final String WOLF = "\uD83D\uDC3A";
    private static final String BOA = "\uD83D\uDC0D";
    private static final String FOX = "\uD83E\uDD8A";
    private static final String EAGLE = "\uD83E\uDD85";
    private static final String RABBIT = "\uD83D\uDC07";
    private static final String DUCK = "\uD83E\uDD86";
    private static final String MOUSE = "\uD83D\uDC01";
    private static final String CATERPILLAR = "\uD83D\uDC1B";
    private static final String GRASS = "\uD83C\uDF31";

    private static int dayCount;

    public static Island island;
    private static int wolf;
    private static int boa;
    private static int fox;
    private static int bear;
    private static int eagle;
    private static int horse;
    private static int deer;
    private static int rabbit;
    private static int mouse;
    private static int goat;
    private static int sheep;
    private static int boar;
    private static int buffalo;
    private static int duck;
    private static int caterpillar;
    private static int grass;

    private static int deaAnimals;
    private static int bornAnimals;

    public static void render() {
        dayCount++;
        reset();
        IslandCell[][] grid = island.getIslandGrid();
        countAnimals(grid);
        print();
    }

    private static void reset() {
        wolf = 0;
        boa = 0;
        fox = 0;
        bear = 0;
        eagle = 0;
        horse = 0;
        deer = 0;
        rabbit = 0;
        mouse = 0;
        goat = 0;
        sheep = 0;
        boar = 0;
        buffalo = 0;
        duck = 0;
        caterpillar = 0;
        grass = 0;
        deaAnimals = 0;
        bornAnimals = 0;
    }

    private static void countAnimals(IslandCell[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                IslandCell cell = grid[y][x];
                deaAnimals += cell.getNumberOfDeadAnimals();
                bornAnimals += cell.getNumberOfBornAnimals();
                List<Animal> animals = cell.getAnimals();
                grass += cell.plants;
                for (Animal animal : animals) {
                    String name = animal.getClass().getSimpleName();
                    switch (name) {
                        case "Wolf" -> wolf++;
                        case "Boa" -> boa++;
                        case "Fox" -> fox++;
                        case "Bear" -> bear++;
                        case "Eagle" -> eagle++;
                        case "Horse" -> horse++;
                        case "Deer" -> deer++;
                        case "Rabbit" -> rabbit++;
                        case "Mouse" -> mouse++;
                        case "Goat" -> goat++;
                        case "Sheep" -> sheep++;
                        case "Boar" -> boar++;
                        case "Buffalo" -> buffalo++;
                        case "Duck" -> duck++;
                        case "Caterpillar" -> caterpillar++;
                    }
                }
            }
        }
    }

    private static void print() {
        System.out.println("Day number " + dayCount);
        System.out.println("Number of dead animals: " + deaAnimals);
        System.out.println("Number of born animals: " + bornAnimals);
        System.out.printf("| %s:%s | %s:%s | %s:%s | %s:%s | %s:%s | %s:%s | %s:%s " +
                        "| %s:%s | %s:%s | %s:%s | %s:%s | %s:%s | %s:%s | %s:%s | %s:%s | %s:%s |"
                , BUFFALO, buffalo
                , BEAR, bear
                , HORSE, horse
                , DEER, deer
                , BOAR, boar
                , SHEEP, sheep
                , GOAT, goat
                , WOLF, wolf
                , BOA, boa
                , FOX, fox
                , EAGLE, eagle
                , RABBIT, rabbit
                , DUCK, duck
                , MOUSE, mouse
                , CATERPILLAR, caterpillar
                , GRASS, grass);
        System.out.println("\n");
    }

    @Override
    public void run() {
        render();
    }
}
