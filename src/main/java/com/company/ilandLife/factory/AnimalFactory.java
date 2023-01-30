package com.company.ilandLife.factory;

import com.company.ilandLife.animal.Animal;
import com.company.ilandLife.animal.herbivore.*;
import com.company.ilandLife.animal.predator.*;

public class AnimalFactory {
    public static Animal getAnimal(AnimalTypes animalType) {
        return switch (animalType) {
            case WOLF -> new Wolf();
            case BOA -> new Boa();
            case FOX -> new Fox();
            case BEAR -> new Bear();
            case EAGLE -> new Eagle();
            case HORSE -> new Horse();
            case DEER -> new Deer();
            case RABBIT -> new Rabbit();
            case MOUSE -> new Mouse();
            case GOAT -> new Goat();
            case SHEEP -> new Sheep();
            case BOAR -> new Boar();
            case BUFFALO -> new Buffalo();
            case DUCK -> new Duck();
            case CATERPILLAR -> new Caterpillar();
        };
    }

    public static String getAnimalName(AnimalTypes animalType) {
        return switch (animalType) {
            case WOLF -> "Wolf";
            case BOA -> "Boa";
            case FOX -> "Fox";
            case BEAR -> "Bear";
            case EAGLE -> "Eagle";
            case HORSE -> "Horse";
            case DEER -> "Deer";
            case RABBIT -> "Rabbit";
            case MOUSE -> "Mouse";
            case GOAT -> "Goat";
            case SHEEP -> "Sheep";
            case BOAR -> "Boar";
            case BUFFALO -> "Buffalo";
            case DUCK -> "Duck";
            case CATERPILLAR -> "Caterpillar";
        };
    }
}
