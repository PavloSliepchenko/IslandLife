package com.company.ilandLife.island;

import com.company.ilandLife.animal.Animal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Island {
    private int x;
    private int y;
    private IslandCell[][] islandGrid;
    private List<Animal> allAnimals = Collections.synchronizedList(new ArrayList<>());

    public Island(int xDimension, int yDimension) {
        this.x = xDimension;
        this.y = yDimension;
        this.islandGrid = new IslandCell[yDimension][xDimension];
        for (int y = 0; y < yDimension; y++) {
            for (int x = 0; x < xDimension; x++) {
                islandGrid[y][x] = new IslandCell(x, y, this);
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void addAnimal(Animal animal) {
        this.allAnimals.add(animal);
    }

    public void updateAnimalList() {
        allAnimals = new ArrayList<>();
        for (int y = 0; y < islandGrid.length; y++) {
            for (int x = 0; x < islandGrid[y].length; x++) {
                IslandCell cell = islandGrid[y][x];
                List<Animal> animals = cell.getAnimals();
                for(Animal animal : animals) {
                    allAnimals.add(animal);
                }
            }
        }
    }

    public IslandCell[][] getIslandGrid() {
        return islandGrid;
    }

    public List<Animal> getAliveAnimals() {
        updateAnimalList();
        return allAnimals;
    }
}
