package com.company.ilandLife.animal.predator;

import java.util.concurrent.ThreadLocalRandom;

public class Boa extends Predator {
    public Boa() {
        isMale = ThreadLocalRandom.current().nextBoolean();
    }

}
