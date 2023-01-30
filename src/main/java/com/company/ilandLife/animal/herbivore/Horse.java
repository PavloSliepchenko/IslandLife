package com.company.ilandLife.animal.herbivore;

import java.util.concurrent.ThreadLocalRandom;

public class Horse extends Herbivore {
    public Horse() {
        isMale = ThreadLocalRandom.current().nextBoolean();
    }
}
