package com.company.ilandLife.animal.herbivore;

import java.util.concurrent.ThreadLocalRandom;

public class Sheep extends Herbivore {
    public Sheep() {
        isMale = ThreadLocalRandom.current().nextBoolean();
    }
}
