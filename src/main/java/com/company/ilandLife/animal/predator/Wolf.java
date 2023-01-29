package com.company.ilandLife.animal.predator;

import com.company.ilandLife.animal.Animal;

import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends Animal {
    public Wolf() {
        isPredator = true;
        isMale = ThreadLocalRandom.current().nextBoolean();
    }

    @Override
    public void run() {
        eat();
        breed();
        moveToAnotherCell();
    }
}
