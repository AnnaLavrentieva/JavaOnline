package com.lavrentieva.service;

import com.lavrentieva.model.Car;

import java.util.Random;

public class CarService {

    public Car create() {
        Car car;
        car = new Car("man " + new Random().nextInt(1000),
                "eng " + new Random().nextInt(1000),
                "col " + new Random().nextInt(1000));
        return car;
    }

    public void print(Car car) {
        System.out.println("Manufacturer: " + car.getManufacturer() + ", Engine: " + car.getEngine() +
                ", Color: " + car.getColor() + ", Count: " + car.getCount() + ", Price: "
                + car.getPrice());
    }

}

