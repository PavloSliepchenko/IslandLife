package com.company.ilandLife;

import com.company.ilandLife.config.SimulationConfig;
import com.company.ilandLife.island.Island;
import com.company.ilandLife.simulate.Simulator;


public class Main {
    public static void main(String[] args) {
        Island island = new Island(SimulationConfig.xDimension, SimulationConfig.yDimension);
        Simulator simulator = new Simulator(SimulationConfig.numberOfDays, island);
        simulator.simulate();
    }
}
