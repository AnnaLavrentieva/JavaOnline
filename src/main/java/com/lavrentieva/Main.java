package com.lavrentieva;

import com.lavrentieva.model.Car;
import com.lavrentieva.repository.CarArrayRepository;
import com.lavrentieva.service.CarService;

public class Main {
    public static void main(String[] args) {
        final CarService carService = new CarService(new CarArrayRepository());

        final Car car = carService.create();
        carService.print(car);
        carService.check(car);
        System.out.println();

        System.out.println(carService.find(car.getId()));

        carService.create(4);
        carService.printAll();
        final Car[] all = carService.getAll();

        Car carFromArray = all[0];
        carService.delete(carFromArray.getId());
        carService.printAll();

        carFromArray = all[2];
        carService.changeRandomColor(carFromArray.getId());
        System.out.println(carService.find(carFromArray.getId()));
    }
}