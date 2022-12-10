package com.lavrentieva.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.UUID;

@Getter
@Setter

public abstract class Car implements CountRestore {
    protected final String id;
    protected String manufacturer;
    protected Engine engine;
    protected Color color;
    protected int count;
    protected int price;
    protected Type type;
    private Random random = new Random();

    protected Car() {
        this.id = UUID.randomUUID().toString();
    }

    protected Car(String manufacturer, Color color, Type type) {
        count = 1;
        price = random.nextInt(1000);
        this.manufacturer = manufacturer;
        engine = new Engine();
        this.color = color;
        this.id = UUID.randomUUID().toString();
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %s, %s %n", "Type: " + type, "Id: " + id,
                "Manufacturer: " + manufacturer, "Engine: " + engine, "Color: " + color,
                "Count: " + count, "Price: " + price);
    }
}
