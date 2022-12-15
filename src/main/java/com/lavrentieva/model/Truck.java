package com.lavrentieva.model;

public class Truck extends Car {
    private int loadCapacity;

    public Truck(String manufacturer, Color color, Type type, int loadCapacity) {
        super(manufacturer, color, type);
        super.count = restore();
        this.loadCapacity = loadCapacity;
    }

    @Override
    public int restore() {
        System.out.println("Count was restore");
        return 50;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", super.toString(), "Load Capacity: " + loadCapacity);
    }
}
