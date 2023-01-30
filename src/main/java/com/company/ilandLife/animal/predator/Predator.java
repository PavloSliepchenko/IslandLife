package com.company.ilandLife.animal.predator;

import com.company.ilandLife.animal.Animal;
import com.company.ilandLife.animal.herbivore.Herbivore;
import com.company.ilandLife.config.EatingPossibilityConfig;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Predator extends Animal {
    @Override
    public void eat() {
        int localDay = day;
        while (amountOfFoodEaten < amountOfFoodNeeded) {
            if (checkDayChange(localDay, day)) {
                localDay = day;
            }

            if (daysHungry > 3) {
                die(this);
                return;
            }

            if (daysHungry > 1) {
                moveToAnotherCell();
            }
            List<Animal> possibleVictims = cell.getAnimals()
                    .stream()
                    .filter(a -> a instanceof Herbivore)
                    .collect(Collectors.toList());

            if (possibleVictims.size() > 0) {
                Animal victim = hunt(possibleVictims);

                if (victim != null) {
                    synchronized (victim) {
                        double portionOfVictimCanBeEaten = 0.7;
                        this.amountOfFoodEaten += victim.weight * portionOfVictimCanBeEaten;
                        die(victim);
                    }
                }
            }
        }
    }

    private Animal hunt(List<Animal> victims) {
        String predatorName = this.getClass().getSimpleName();
        int possibilityToEat = 0;
        Animal victim = null;
        for (Animal animal : victims) {
            int possibility = (int) EatingPossibilityConfig.eatingPossibility(predatorName,
                    animal.getClass().getSimpleName());

            if (possibility > possibilityToEat) {
                possibilityToEat = possibility;
                victim = animal;
            }
        }
        return victim;
    }
}
