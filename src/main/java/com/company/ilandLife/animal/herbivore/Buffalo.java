package com.company.ilandLife.animal.herbivore;

import java.util.concurrent.ThreadLocalRandom;

public class Buffalo extends Herbivore {
    public Buffalo() {
        isMale = ThreadLocalRandom.current().nextBoolean();
    }
}
