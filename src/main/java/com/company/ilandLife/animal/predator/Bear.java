package com.company.ilandLife.animal.predator;

import java.util.concurrent.ThreadLocalRandom;

public class Bear extends Predator {

    public Bear() {
        isMale = ThreadLocalRandom.current().nextBoolean();
    }

    @Override
    public void run() {
        eat();
        breed();
        moveToAnotherCell();
    }

}
