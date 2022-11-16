package com.lavrentieva.model;

import java.util.Random;

public class Car {
    private String manufacturer;
    private String engine;
    private String color;
    private int count;
    private int price;

    public Car() {
    }

    public Car(String manufacturer, String engine, String color) {
        count = 1;
        price = new Random().nextInt(1000);
        this.manufacturer = manufacturer;
        this.engine = engine;
        this.color = color;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getEngine() {
        return engine;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setCount() {
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
}
