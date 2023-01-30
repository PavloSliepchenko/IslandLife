package com.company.ilandLife.animal.predator;

import java.util.concurrent.ThreadLocalRandom;
public class Wolf extends Predator {
    public Wolf() {
        isMale = ThreadLocalRandom.current().nextBoolean();
    }
}
