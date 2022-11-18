package com.lavrentieva.service;

import com.lavrentieva.model.Car;
import com.lavrentieva.model.Color;

import java.util.Random;

public class CarService {
    private final Random random = new Random();
    Car car;

    public Car create() {
        car = new Car(getRandomManufacturer(), getRandomColor());
        return car;
    }

    private Color getRandomColor() {
        final Color[] values = Color.values();
        final int randomIndex = random.nextInt(values.length);
        return values[randomIndex];
    }

    private String getRandomManufacturer() {
        return "man " + random.nextInt(1000);
    }

    public void print(Car car) {
        System.out.println("Manufacturer: " + getRandomManufacturer() + ", Engine: " + car.toString() +
                ", Color: " + getRandomColor() + ", Count: " + car.getCount() + ", Price: "
                + car.getPrice());
    }

    public static void check(Car car) {
        String check;
        if (car.getCount() > 0) {
            check = checkIfCountTrue(car);
        } else {
            check = checkIfCountFalse(car);
        }
        System.out.println(check);
    }

    private static String checkIfCountTrue(Car car) {
        String resultTrue = (car.engine.getPower() > 200) ?
                "The car is ready for sale" : "The car is not ready for sale, " +
                "power do not match the condition";
        return resultTrue;
    }

    private static String checkIfCountFalse(Car car) {
        String resultFalse = car.engine.getPower() > 200 ?
                "The car is not ready for sale, count do not match the condition" :
                "The car is not ready for sale, count and power do not match the condition";
        return resultFalse;
    }
}
