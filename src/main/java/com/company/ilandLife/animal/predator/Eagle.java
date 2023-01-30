package com.company.ilandLife.animal.predator;

import java.util.concurrent.ThreadLocalRandom;

public class Eagle extends Predator {
    public Eagle() {
        isMale = ThreadLocalRandom.current().nextBoolean();
    }
}
