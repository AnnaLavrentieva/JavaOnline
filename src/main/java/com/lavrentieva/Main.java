package com.lavrentieva;

import com.lavrentieva.model.Car;
import com.lavrentieva.model.Type;
import com.lavrentieva.repository.CarArrayRepository;
import com.lavrentieva.service.CarService;
import com.lavrentieva.util.AlgorithmUtil;
import com.lavrentieva.util.RandomGenerator;


public class Main {
    public static void main(String[] args) {
        final CarService carService = new CarService(new CarArrayRepository());

        Car car = carService.create(Type.CAR);
        carService.printCar(car);
        carService.check(car);
        System.out.println();

        Car car1 = carService.create(Type.TRUCK);
        carService.printCar(car1);
        carService.check(car1);
        System.out.println();

        carService.carEquals(car, car1);
        System.out.println();

        System.out.println(carService.find(car.getId()));
        System.out.println();

        carService.create(4);
        Car[] all = carService.getAll();
        System.out.println();

        AlgorithmUtil.bubbleSort(all);
        Car searchCar = AlgorithmUtil.binarySearch(car1, all);
        System.out.println(searchCar);
        System.out.println();

        Car carFromArray = all[2];
        carService.delete(carFromArray.getId());
        carService.printAll();
        System.out.println();

        carFromArray = all[1];
        carService.changeRandomColor(carFromArray.getId());
        System.out.println(carService.find(carFromArray.getId()));
        System.out.println();

        carService.createCarFromRandomAmount(new RandomGenerator());
        System.out.println();

        carService.printManufacturerAndCount(car);
        System.out.println();

        carService.printColor(car);
        System.out.println();

        carService.checkCount(car);
        System.out.println();

        carService.printEngineInfo(car);
        System.out.println();

        carService.printInfo(car);
        System.out.println();
    }
}