package com.company.ilandLife.simulate;

import com.company.ilandLife.animal.Animal;
import com.company.ilandLife.island.Island;
import com.company.ilandLife.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class Simulator {
    private int numberOfDaysToSimulate;
    private int currentDay = 0;
    private Island island;


    public Simulator(int numberOfDaysToSimulate, Island island) {
        this.numberOfDaysToSimulate = numberOfDaysToSimulate;
        this.island = island;
    }

    public void simulate() {
       ExecutorService executor = Executors.newCachedThreadPool();
        View view = new View(island);
        ScheduledExecutorService render = Executors.newScheduledThreadPool(1);
        render.scheduleAtFixedRate(view, 0, 1, TimeUnit.SECONDS);

        while (currentDay < numberOfDaysToSimulate && island.getAliveAnimals().size() > 0) {
            List<Future> tasks = new ArrayList<>();
            island.getAliveAnimals().forEach(animal -> {
                        tasks.add(executor.submit(animal));
                    }
            );
            for (Future task : tasks) {
                try {
                    task.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }

            currentDay++;

            for (Animal animal : island.getAliveAnimals()) {
                animal.setDay(currentDay);
            }
        }
    }
}

