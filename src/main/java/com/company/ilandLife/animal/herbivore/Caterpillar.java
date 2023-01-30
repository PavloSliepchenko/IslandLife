package com.company.ilandLife.animal.herbivore;

import java.util.concurrent.ThreadLocalRandom;

public class Caterpillar extends Herbivore {
    public Caterpillar() {
        isMale = ThreadLocalRandom.current().nextBoolean();
    }
}
