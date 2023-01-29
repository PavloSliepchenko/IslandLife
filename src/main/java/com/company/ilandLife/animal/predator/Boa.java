package com.company.ilandLife.animal.predator;

import com.company.ilandLife.animal.Animal;

import java.util.concurrent.ThreadLocalRandom;

public class Boa extends Animal {
    public Boa() {
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
