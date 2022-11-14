package com.lavrentieva;

import com.lavrentieva.service.CarService;

public class Main {
    public static void main(String[] args) {
        CarService carMain = new CarService();
        carMain.cars[0] = carMain.create();
        carMain.cars[1] = carMain.create();
        carMain.cars[2] = carMain.create();

        carMain.print(carMain.cars[0]);
        carMain.print(carMain.cars[1]);
        carMain.print(carMain.cars[2]);
    }
}
