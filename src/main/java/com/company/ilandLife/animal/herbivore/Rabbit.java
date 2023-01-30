package com.company.ilandLife.animal.herbivore;

import java.util.concurrent.ThreadLocalRandom;

public class Rabbit extends Herbivore {
    public Rabbit() {
        isMale = ThreadLocalRandom.current().nextBoolean();
    }
}
