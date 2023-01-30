package com.company.ilandLife.animal.herbivore;

import java.util.concurrent.ThreadLocalRandom;

public class Boar extends Herbivore {
    public Boar() {
        isMale = ThreadLocalRandom.current().nextBoolean();
    }
}
