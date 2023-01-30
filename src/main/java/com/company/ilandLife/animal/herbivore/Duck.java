package com.company.ilandLife.animal.herbivore;

import java.util.concurrent.ThreadLocalRandom;

public class Duck extends Herbivore {
    public Duck() {
        isMale = ThreadLocalRandom.current().nextBoolean();
    }
}
