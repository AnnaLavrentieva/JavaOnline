package com.lavrentieva.model;

import java.util.Random;

public class Car {
    private String manufacturer;
    private Engine engine;
    private Color color;
    private int count;
    private int price;
    private Random random = new Random();

    public Car() {
    }

    public Car(String manufacturer, Color color) {
        count = 1;
        price = random.nextInt(1000);
        this.manufacturer = manufacturer;
        engine = new Engine();
        this.color = color;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String toString() {
        return engine.getType() + " power" + engine.getPower();
    }
    public Engine getEngine() {
        return engine;
    }
}
