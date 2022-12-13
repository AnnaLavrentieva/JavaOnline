package com.lavrentieva.service;

import com.lavrentieva.model.*;
import com.lavrentieva.repository.CarArrayRepository;
import com.lavrentieva.util.RandomGenerator;

import java.util.Arrays;
import java.util.Random;

public class CarService {
    private final CarArrayRepository carArrayRepository;
    private final Random random = new Random();
    PassengerCar passengerCar;
    Truck truck;

    public CarService(final CarArrayRepository carArrayRepository) {
        this.carArrayRepository = carArrayRepository;
    }

    public PassengerCar createPassengerCar() {
        passengerCar = new PassengerCar(getRandomManufacturer(), getRandomColor(), Type.getCAR(),
                getRandomPassengerCount());
        carArrayRepository.save(passengerCar);
        return passengerCar;
    }

    public Truck createTruck() {
        truck = new Truck(getRandomManufacturer(), getRandomColor(), Type.getTRUCK(),
                getRandomLoadCapacity());
        return truck;
    }

    public void create(final int count) {
        for (int i = 0; i < count; i++) {
            createPassengerCar();
            printPassengerCar(passengerCar);
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

    private int getRandomPassengerCount() {
        return random.nextInt(0, 5);
    }

    private int getRandomLoadCapacity() {
        return random.nextInt(0, 1000);
    }

    public void printPassengerCar(final PassengerCar passengerCar) {
        System.out.println(passengerCar);
    }

    public static void check(final PassengerCar passengerCar) {
        String check;
        if (passengerCar.getCount() > 0) {
            check = checkIfCountTrue(passengerCar);
        } else {
            check = checkIfCountFalse(passengerCar);
        }
        System.out.println(check);
    }

    private static String checkIfCountTrue(final PassengerCar passengerCar) {
        String resultTrue = (passengerCar.getEngine().getPower() > 200) ?
                "The car is ready for sale" : "The car is not ready for sale, " +
                "power do not match the condition";
        return resultTrue;
    }

    private static String checkIfCountFalse(final PassengerCar passengerCar) {
        String resultFalse = passengerCar.getEngine().getPower() > 200 ?
                "The car is not ready for sale, count do not match the condition" :
                "The car is not ready for sale, count and power do not match the condition";
        return resultFalse;
    }

    public PassengerCar[] getAll() {
        return carArrayRepository.getAll();
    }

    public void printAll() {
        PassengerCar[] all = carArrayRepository.getAll();
        System.out.println(Arrays.toString(all));
    }

    public PassengerCar find(final String id) {
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
        final PassengerCar passengerCar = find(id);
        if (passengerCar == null) {
            return;
        }
        findAndChangeRandomColor(passengerCar);
    }

    private void findAndChangeRandomColor(PassengerCar passengerCar) {
        final Color color = passengerCar.getColor();
        Color randomColor;
        do {
            randomColor = getRandomColor();
        } while (randomColor == color);
        carArrayRepository.updateColor(passengerCar.getId(), randomColor);
    }

}
