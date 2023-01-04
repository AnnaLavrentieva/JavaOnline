package com.lavrentieva.model;

import java.util.Random;

public class Truck extends Car {
    private int loadCapacity;
    private final Random random = new Random();

    public Truck(String manufacturer, Color color, Type type, int loadCapacity) {
        super(manufacturer, color, type);
        super.count = restore();
        this.loadCapacity = loadCapacity;
    }

    @Override
    public int restore() {
        System.out.println("Count was restore");
        return random.nextInt(1, 100);
    }

    @Override
    public String toString() {
        return String.format("%s, %s", super.toString(), "Load Capacity: " + loadCapacity);
    }
}
