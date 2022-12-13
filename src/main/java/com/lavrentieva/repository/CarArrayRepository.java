package com.lavrentieva.repository;

import com.lavrentieva.model.Color;
import com.lavrentieva.model.PassengerCar;

public class CarArrayRepository {
    private static PassengerCar[] cars = new PassengerCar[10];

    public void save(final PassengerCar passengerCar) {
        final int index = putCar(passengerCar);
        if (index == cars.length) {
            final int oldLength = cars.length;
            increaseArray();
            cars[oldLength] = passengerCar;
        }
    }

    private int putCar(final PassengerCar passengerCar) {
        int index = 0;
        for (; index < cars.length; index++) {
            if (cars[index] == null) {
                cars[index] = passengerCar;
                break;
            }
        }
        return index;
    }

    private void increaseArray() {
        PassengerCar[] newCars = new PassengerCar[cars.length * 2];
        System.arraycopy(cars, 0, newCars, 0, cars.length);
        cars = newCars;
    }

    public PassengerCar[] getAll() {
        int newLength = foundLength();
        PassengerCar[] newCars = new PassengerCar[newLength];
        System.arraycopy(cars, 0, newCars, 0, newLength);
        return newCars;
    }

    private int foundLength() {
        int newLength = 0;
        for (PassengerCar passengerCar : cars) {
            if (passengerCar != null) {
                newLength++;
            } else {
                break;
            }
        }
        return newLength;
    }

    public PassengerCar getById(final String id) {
        for (PassengerCar passengerCar : cars) {
            if (passengerCar.getId().equals(id)) {
                return passengerCar;
            }
        }
        return null;
    }

    public void delete(final String id) {
        int index = 0;
        for (; index < cars.length; index++) {
            if (cars[index].getId().equals(id)) {
                break;
            }
        }
        if (index != cars.length) {
            System.arraycopy(cars, index + 1, cars, index, cars.length - (index + 1));
        }
    }

    public void updateColor(final String id, Color color) {
        PassengerCar passengerCar = getById(id);
        if (passengerCar != null) {
            passengerCar.setColor(color);
        }
    }
}
