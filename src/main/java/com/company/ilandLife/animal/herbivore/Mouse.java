package com.company.ilandLife.animal.herbivore;

import java.util.concurrent.ThreadLocalRandom;

public class Mouse extends Herbivore {
    public Mouse() {
        isMale = ThreadLocalRandom.current().nextBoolean();

    }
}
