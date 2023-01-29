package com.company.ilandLife.simulate;

import com.company.ilandLife.animal.Animal;
import com.company.ilandLife.island.Island;
import com.company.ilandLife.island.IslandCell;
import com.company.ilandLife.view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Simulator {
    private int numberOfDaysToSimulate;
    private int currentDay = 0;
    private Island island;


    public Simulator(int numberOfDaysToSimulate, Island island) {
        this.numberOfDaysToSimulate = numberOfDaysToSimulate;
        this.island = island;
    }

    public void simulate() {
       /* IslandCell[][] grid = island.getIslandGrid();
        View view = new View();
        view.island = this.island;
        view.run();
        while (currentDay < numberOfDaysToSimulate) {
            for (Animal animal : island.getAllAnimals()) {
                animal.run();
            }

            currentDay++;

            for (Animal animal : island.getAllAnimals()) {
                animal.setDay(currentDay);
            }


            island.updateAnimalList();
            view.run();
        }*/
        /*View view = new View();
        view.island = this.island;
        view.run();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (Animal animal : island.getAllAnimals()) {
            executor.submit(animal);
        }
        currentDay++;
        for (Animal animal : island.getAllAnimals()) {
            animal.setDay(currentDay);
        }
        view.run();*/


        //TO DO
    }
}

