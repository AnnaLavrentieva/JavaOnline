package com.lavrentieva.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.UUID;

@Getter
@Setter

public class Car {
    private final String id;
    private String manufacturer;
    private Engine engine;
    private Color color;
    private int count;
    private int price;
    private Random random = new Random();

    public Car() {
        this.id = UUID.randomUUID().toString();
    }

    public Car(String manufacturer, Color color) {
        count = 1;
        price = random.nextInt(1000);
        this.manufacturer = manufacturer;
        engine = new Engine();
        this.color = color;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %s %n", "Id: " + id, "Manufacturer: " + manufacturer,
                "Engine: " + engine, "Color: " + color, "Count: " + count, "Price: " + price);
    }
}
