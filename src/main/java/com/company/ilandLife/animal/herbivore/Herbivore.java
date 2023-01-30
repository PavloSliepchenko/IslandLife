package com.company.ilandLife.animal.herbivore;

import com.company.ilandLife.animal.Animal;

public abstract class Herbivore extends Animal {
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
            synchronized (this.cell.plants) {
                if (this.cell.plants > this.amountOfFoodNeeded) {
                    this.amountOfFoodEaten = this.amountOfFoodNeeded;
                    this.cell.plants -= this.amountOfFoodNeeded;
                } else {
                    this.amountOfFoodEaten = cell.plants;
                    this.cell.plants = 0.0;
                }
            }
        }
    }
}
