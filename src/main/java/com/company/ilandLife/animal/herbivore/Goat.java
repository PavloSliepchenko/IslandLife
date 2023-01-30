package com.company.ilandLife.animal.herbivore;

import java.util.concurrent.ThreadLocalRandom;

public class Goat extends Herbivore {
    public Goat() {
        isMale = ThreadLocalRandom.current().nextBoolean();
    }
}
