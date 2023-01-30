package com.company.ilandLife.animal.predator;

import java.util.concurrent.ThreadLocalRandom;

public class Fox extends Predator {
    public Fox() {
        isMale = ThreadLocalRandom.current().nextBoolean();
    }
}
