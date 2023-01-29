package com.company.ilandLife.island;

import com.company.ilandLife.animal.Animal;
import com.company.ilandLife.config.AnimalFeaturesConfig;
import com.company.ilandLife.factory.AnimalFactory;
import com.company.ilandLife.factory.AnimalTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class IslandCell {
    private final int MAX_NUMBER_OF_PLANTS = 200;
    private final String MAX_NUMBER_OF_SPECIES = "max number of the species on the cell";
    private int numberOfDeadAnimals;
    private int numberOfBornAnimals;
    private int x;
    private int y;
    private List<Animal> animals;
    private Island island;
    public Double plants = ThreadLocalRandom.current().nextDouble(MAX_NUMBER_OF_PLANTS);

    public IslandCell(int x, int y, Island island) {
        this.x = x;
        this.y = y;
        this.island = island;
        this.animals = populateIslandCell();

    }

    private List<Animal> populateIslandCell() {
        List<Animal> result = new ArrayList<>();
        AnimalTypes[] animalTypes = AnimalTypes.values();
        for (int i = 0; i < animalTypes.length; i++) {
            AnimalTypes animalType = animalTypes[i];
            int bound = (int) AnimalFeaturesConfig.getParameter(animalType.name(), MAX_NUMBER_OF_SPECIES);
            int numberOfAnimals = ThreadLocalRandom.current().nextInt(bound);
            for (int j = 0; j < numberOfAnimals; j++) {
                Animal animal = AnimalFactory.getAnimal(animalType);
                animal.setCell(this);
                animal.setIsland(island);
                result.add(animal);
                island.addAnimal(animal);
            }
        }
        return result;
    }

    public synchronized void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public synchronized void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public synchronized void addDead() {
        numberOfDeadAnimals++;
    }

    public synchronized void addBorn() {
        numberOfBornAnimals++;
    }

    public int getNumberOfDeadAnimals() {
        return numberOfDeadAnimals;
    }

    public int getNumberOfBornAnimals() {
        return numberOfBornAnimals;
    }

    public void reset() {
        numberOfDeadAnimals = 0;
        numberOfBornAnimals = 0;
    }
}
