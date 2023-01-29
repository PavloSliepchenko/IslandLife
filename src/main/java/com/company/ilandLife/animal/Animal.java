package com.company.ilandLife.animal;

import com.company.ilandLife.config.AnimalFeaturesConfig;
import com.company.ilandLife.config.EatingPossibilityConfig;
import com.company.ilandLife.factory.AnimalFactory;
import com.company.ilandLife.factory.AnimalTypes;
import com.company.ilandLife.island.Island;
import com.company.ilandLife.island.IslandCell;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public abstract class Animal implements Runnable {
    private static final String WEIGHT_PARAMETER = "weight";
    private static final String FOOD_PARAMETER = "amount of food needed";
    private static final String SPEED_PARAMETER = "max speed";
    private final double weight =
            AnimalFeaturesConfig.getParameter(this.getClass().getSimpleName(), WEIGHT_PARAMETER);
    private final double amountOfFoodNeeded =
            AnimalFeaturesConfig.getParameter(this.getClass().getSimpleName(), FOOD_PARAMETER);
    private final double maxSpeed =
            AnimalFeaturesConfig.getParameter(this.getClass().getSimpleName(), SPEED_PARAMETER);
    public boolean isPredator;
    public boolean isMale;
    private IslandCell cell;
    private Island island;
    private double amountOfFoodEaten;
    private int daysHungry = 0;
    private int day = 0;


    public synchronized void eat() {
        //System.out.println(this.getClass().getSimpleName() + " eats");
        int localDay = day;
        while (amountOfFoodEaten < amountOfFoodNeeded) {
            if (checkDayChange(localDay, day)) {
                localDay = day;
            }

            if (daysHungry > 3) {
                die(this);
            }

            if (daysHungry > 1) {
                moveToAnotherCell();
            }

            if (isPredator) {
                List<Animal> possibleVictims = cell.getAnimals()
                        .stream()
                        .filter(a -> !a.isPredator)
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
                //System.out.println(this.getClass().getSimpleName() + " has eaten successfully");
            } else {
                synchronized (this.cell.plants) {
                    if (this.cell.plants > this.amountOfFoodNeeded) {
                        this.amountOfFoodEaten = this.amountOfFoodNeeded;
                        this.cell.plants -= this.amountOfFoodNeeded;
                    } else {
                        this.amountOfFoodEaten = cell.plants;
                        this.cell.plants = 0.0;
                    }
                    //System.out.println(this.getClass().getSimpleName() + " has eaten successfully");
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

    private synchronized void die(Animal animal) {
        cell.removeAnimal(animal);
        cell.addDead();
        animal = null;
    }

    private boolean checkDayChange(int localDay, int day) {
        if (localDay != day) {
            if (amountOfFoodEaten < amountOfFoodNeeded) {
                daysHungry++;
            }
            amountOfFoodEaten = 0;
            cell.reset();
            return true;
        }
        return false;
    }

    public synchronized void breed() {
        //System.out.println(this.getClass().getSimpleName() + " breeds");
        boolean wantsToBreed = ThreadLocalRandom.current().nextBoolean();
        if (wantsToBreed) {
            Animal partner = searchForThePartner(this);
            if (partner != null) {
                synchronized (partner) {
                    Animal child = AnimalFactory.getAnimal(AnimalTypes.valueOf(this.getClass().getSimpleName()));
                    child.setCell(cell);
                    child.setIsland(island);
                    cell.addAnimal(child);
                    cell.addBorn();

                }
            }
        }
    }

    private Animal searchForThePartner(Animal animal) {
        for (Animal partner : cell.getAnimals()) {
            if (partner.getClass().getSimpleName() == animal.getClass().getSimpleName()
                    && partner != animal
                    && ((partner.isMale && !animal.isMale) || (!partner.isMale && animal.isMale))) {
                boolean partnerWantsToBreed = ThreadLocalRandom.current().nextBoolean();
                return partnerWantsToBreed ? partner : null;
            }
        }
        return null;
    }

    public synchronized void moveToAnotherCell() {
        //System.out.println(this.getClass().getSimpleName() + " moves to another cell");
        for (int i = 0; i < maxSpeed; i++) {
            boolean wantsToMove = ThreadLocalRandom.current().nextBoolean();
            if (wantsToMove) {
                Direction direction = chooseDirection();
                relocate(direction);
            }
        }
    }

    private Direction chooseDirection() {
        //System.out.println(this.island);
        while (true) {
            Direction[] directions = Direction.values();
            Direction direction = directions[ThreadLocalRandom.current().nextInt(directions.length)];

            if (direction == Direction.UP && cell.getY() > 0) {
                return direction;
            } else if (direction == Direction.DOWN && cell.getY() < island.getY() - 1) {
                return direction;
            } else if (direction == Direction.LEFT && cell.getX() > 0) {
                return direction;
            } else if (direction == Direction.RIGHT && cell.getX() < island.getX() - 1) {
                return direction;
            }
        }
    }

    private void relocate(Direction direction) {
        int newX = -1;
        int newY = -1;

        if (direction == Direction.UP) {
            newX = cell.getX();
            newY = cell.getY() - 1;
        } else if (direction == Direction.DOWN) {
            newX = cell.getX();
            newY = cell.getY() + 1;
        } else if (direction == Direction.LEFT) {
            newX = cell.getX() - 1;
            newY = cell.getY();
        } else if (direction == Direction.RIGHT) {
            newX = cell.getX() + 1;
            newY = cell.getY();
        }
        cell.removeAnimal(this);
        cell = island.getIslandGrid()[newY][newX];
        cell.addAnimal(this);
    }

    public synchronized void setCell(IslandCell cell) {
        this.cell = cell;
    }

    public IslandCell getCell() {
        return this.cell;
    }

    public synchronized void setIsland(Island island) {
        this.island = island;
    }

    public synchronized void setDay(int day) {
        this.day = day;
    }
}
