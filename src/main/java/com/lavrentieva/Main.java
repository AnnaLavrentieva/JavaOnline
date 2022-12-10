package com.lavrentieva;

import com.lavrentieva.model.PassengerCar;
import com.lavrentieva.model.Truck;
import com.lavrentieva.repository.CarArrayRepository;
import com.lavrentieva.service.CarService;
import com.lavrentieva.util.RandomGenerator;


public class Main {
    public static void main(String[] args) {
        final CarService carService = new CarService(new CarArrayRepository());

        PassengerCar passengerCar = carService.createPassengerCar();
        carService.printPassengerCar(passengerCar);
        carService.check(passengerCar);
        System.out.println();

        Truck truck = carService.createTruck();
        System.out.println(truck);

        System.out.println(carService.find(passengerCar.getId()));

        carService.create(4);
        PassengerCar[] all = carService.getAll();
        System.out.println();

        PassengerCar carFromArray = all[2];
        carService.delete(carFromArray.getId());
        carService.printAll();

        carFromArray = all[1];
        carService.changeRandomColor(carFromArray.getId());
        System.out.println(carService.find(carFromArray.getId()));

        carService.createCarFromRandomAmount(new RandomGenerator());

    }
}