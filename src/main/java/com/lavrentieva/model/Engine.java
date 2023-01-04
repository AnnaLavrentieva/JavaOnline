package com.lavrentieva.model;

import java.util.Random;

public class Engine {
    final private int power;
    final private String type;
    private Random random = new Random();

    public Engine() {
        type = "type" + random.nextInt(1000);
        power = random.nextInt(1,7);
    }

    public String getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    @Override
    public String toString() {
        return String.format("%s, power %s", type, power);
    }

}
