package com.company.ilandLife.animal.herbivore;

import com.company.ilandLife.animal.Animal;

import java.util.concurrent.ThreadLocalRandom;

public class Horse extends Animal {
    public Horse() {
        isPredator = false;
        isMale = ThreadLocalRandom.current().nextBoolean();
    }

    @Override
    public void run() {
        eat();
        breed();
        moveToAnotherCell();
    }
}
