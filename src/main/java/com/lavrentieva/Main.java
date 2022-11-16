package com.lavrentieva;

import com.lavrentieva.model.Car;
import com.lavrentieva.service.CarService;

public class Main {
    public static void main(String[] args) {
        CarService carService = new CarService();
        final Car carOne = carService.create();
        final Car carTwo = carService.create();
        final Car carThree = carService.create();

        carService.print(carOne);
        carService.print(carTwo);
        carService.print(carThree);
    }
}
