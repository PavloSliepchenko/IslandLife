package com.company.ilandLife.animal.herbivore;

import java.util.concurrent.ThreadLocalRandom;

public class Deer extends Herbivore {
    public Deer() {
        isMale = ThreadLocalRandom.current().nextBoolean();
    }
}
