package com.company.ilandLife.animal.herbivore;

import com.company.ilandLife.animal.Animal;

import java.util.concurrent.ThreadLocalRandom;

public class Deer extends Animal {
    public Deer() {
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
