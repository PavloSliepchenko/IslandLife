package com.company.ilandLife.factory;

import com.company.ilandLife.animal.Animal;
import com.company.ilandLife.animal.herbivore.*;
import com.company.ilandLife.animal.predator.*;

public class AnimalFactory {
    public static Animal getAnimal(AnimalTypes animalType) {
        return switch (animalType) {
            case Wolf -> new Wolf();
            case Boa -> new Boa();
            case Fox -> new Fox();
            case Bear -> new Bear();
            case Eagle -> new Eagle();
            case Horse -> new Horse();
            case Deer -> new Deer();
            case Rabbit -> new Rabbit();
            case Mouse -> new Mouse();
            case Goat -> new Goat();
            case Sheep -> new Sheep();
            case Boar -> new Boar();
            case Buffalo -> new Buffalo();
            case Duck -> new Duck();
            case Caterpillar -> new Caterpillar();
        };
    }
}
