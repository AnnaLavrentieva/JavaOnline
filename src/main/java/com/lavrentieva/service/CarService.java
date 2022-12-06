package com.lavrentieva.service;

import com.lavrentieva.model.Car;
import com.lavrentieva.model.Color;
import com.lavrentieva.repository.CarArrayRepository;
import com.lavrentieva.util.RandomGenerator;

import java.util.Arrays;
import java.util.Random;

public class CarService {
    private final CarArrayRepository carArrayRepository;
    private final Random random = new Random();
    Car car;

    public CarService(final CarArrayRepository carArrayRepository) {
        this.carArrayRepository = carArrayRepository;
    }

    public Car create() {
        car = new Car(getRandomManufacturer(), getRandomColor());
        carArrayRepository.save(car);
        return car;
    }

    public void create(final int count) {
        for (int i = 0; i < count; i++) {
            create();
            print(car);
        }
    }

    public int createCarFromRandomAmount(RandomGenerator randomGenerator) {
        int randomAmount = randomGenerator.randomInteger();
        if (randomAmount == 0) {
            return -1;
        }
        create(randomAmount);
        return randomAmount;
    }

    private Color getRandomColor() {
        final Color[] values = Color.values();
        final int randomIndex = random.nextInt(values.length);
        return values[randomIndex];
    }

    private String getRandomManufacturer() {
        return "man " + random.nextInt(1000);
    }

    public void print(final Car car) {
        System.out.println("ID: " + car.getId() + ", Manufacturer: " + car.getManufacturer() +
                ", Engine: " + car.getEngine() + ", Color: " + car.getColor() + ", Count: " +
                car.getCount() + ", Price: " + car.getPrice());
    }

    public static void check(final Car car) {
        String check;
        if (car.getCount() > 0) {
            check = checkIfCountTrue(car);
        } else {
            check = checkIfCountFalse(car);
        }
        System.out.println(check);
    }

    private static String checkIfCountTrue(final Car car) {
        String resultTrue = (car.getEngine().getPower() > 200) ?
                "The car is ready for sale" : "The car is not ready for sale, " +
                "power do not match the condition";
        return resultTrue;
    }

    private static String checkIfCountFalse(final Car car) {
        String resultFalse = car.getEngine().getPower() > 200 ?
                "The car is not ready for sale, count do not match the condition" :
                "The car is not ready for sale, count and power do not match the condition";
        return resultFalse;
    }

    public Car[] getAll() {
        return carArrayRepository.getAll();
    }

    public void printAll() {
        Car[] all = carArrayRepository.getAll();
        System.out.println(Arrays.toString(all));
    }

    public Car find(final String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        return carArrayRepository.getById(id);
    }

    public void delete(String id) {
        if (id == null || id.isEmpty()) {
            return;
        }
        carArrayRepository.delete(id);
    }

    public void changeRandomColor(String id) {
        if (id == null || id.isEmpty()) {
            return;
        }
        final Car car = find(id);
        if (car == null) {
            return;
        }
        findAndChangeRandomColor(car);
    }

    private void findAndChangeRandomColor(Car car) {
        final Color color = car.getColor();
        Color randomColor;
        do {
            randomColor = getRandomColor();
        } while (randomColor == color);
        carArrayRepository.updateColor(car.getId(), randomColor);
    }

}
