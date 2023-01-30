package com.company.ilandLife.animal;

import com.company.ilandLife.config.AnimalFeaturesConfig;
import com.company.ilandLife.factory.AnimalFactory;
import com.company.ilandLife.factory.AnimalTypes;
import com.company.ilandLife.island.Island;
import com.company.ilandLife.island.IslandCell;

import java.util.concurrent.ThreadLocalRandom;
public abstract class Animal implements Runnable {
    private static final String WEIGHT_PARAMETER = "weight";
    private static final String FOOD_PARAMETER = "amount of food needed";
    private static final String SPEED_PARAMETER = "max speed";
    public final double weight =
            AnimalFeaturesConfig.getParameter(this.getClass().getSimpleName(), WEIGHT_PARAMETER);
    public final double amountOfFoodNeeded =
            AnimalFeaturesConfig.getParameter(this.getClass().getSimpleName(), FOOD_PARAMETER);
    private final double maxSpeed =
            AnimalFeaturesConfig.getParameter(this.getClass().getSimpleName(), SPEED_PARAMETER);
    public boolean isMale;
    public IslandCell cell;
    private Island island;
    public double amountOfFoodEaten;
    public int daysHungry = 0;
    public int day = 0;


    public abstract void eat();

    public synchronized void die(Animal animal) {
        cell.removeAnimal(animal);
        cell.addDead();
        animal = null;
    }

    public boolean checkDayChange(int localDay, int day) {
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
        boolean wantsToBreed = ThreadLocalRandom.current().nextBoolean();
        if (wantsToBreed) {
            Animal partner = searchForThePartner(this);
            if (partner != null) {
                Animal child = AnimalFactory.getAnimal(AnimalTypes.valueOf(this.getClass().getSimpleName().toUpperCase()));
                child.setCell(cell);
                child.setIsland(island);
                cell.addAnimal(child);
                cell.addBorn();
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
        for (int i = 0; i < maxSpeed; i++) {
            boolean wantsToMove = ThreadLocalRandom.current().nextBoolean();
            if (wantsToMove) {
                Direction direction = chooseDirection();
                relocate(direction);
            }
        }
    }

    private Direction chooseDirection() {
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
    @Override
    public void run() {
        eat();
        breed();
        moveToAnotherCell();
    }
}
