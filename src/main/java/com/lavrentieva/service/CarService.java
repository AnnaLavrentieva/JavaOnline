package com.lavrentieva.service;

import com.lavrentieva.model.*;
import com.lavrentieva.repository.CarArrayRepository;
import com.lavrentieva.util.RandomGenerator;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class CarService {
    private final CarArrayRepository carArrayRepository;
    private final Random random = new Random();
    private Car car;

    public CarService(final CarArrayRepository carArrayRepository) {
        this.carArrayRepository = carArrayRepository;
    }

    public Car create(final Type type) {
        if (type.equals(Type.CAR)) {
            car = createPassengerCar();
            carArrayRepository.save(car);
            return car;
        } else if (type.equals(Type.TRUCK)) {
            car = createTruck();
            carArrayRepository.save(car);
            return car;
        }
        return null;
    }

    public Car createPassengerCar() {
        car = new PassengerCar(getRandomManufacturer(), getRandomColor(), Type.getCAR(),
                getRandomPassengerCount());
        return car;
    }

    private Car createTruck() {
        car = new Truck(getRandomManufacturer(), getRandomColor(), Type.getTRUCK(),
                getRandomLoadCapacity());
        return car;
    }

    public void create(final int count) {
        for (int i = 0; i < count; i++) {
            create(getRandomType());
            printCar(car);
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

    public boolean carEquals(Car car1, Car car2) {
        if (car1.getType().equals(car2.getType())) {
            return checkIdHashCodeEquals(car1, car2);
        } else {
            System.out.println("Cars are not equals");
            return false;
        }
    }

    private boolean checkIdHashCodeEquals(Car car1, Car car2) {
        if (car1.getId().hashCode() != car2.getId().hashCode()) {
            System.out.println("Cars are not equals");
            return false;
        } else if (car1.getId().equals(car2.getId())) {
            System.out.println("Cars are equals");
            return true;
        } else {
            System.out.println("Cars are not equals");
            return false;
        }
    }

    private Color getRandomColor() {
        final Color[] values = Color.values();
        final int randomIndex = random.nextInt(values.length);
        return values[randomIndex];
    }

    private Type getRandomType() {
        final Type[] values = Type.values();
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

    public void printCar(Car car) {
        System.out.println(car);
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
        String resultTrue = (car.getEngine().getPower() > 200) ?
                "The car is ready for sale" : "The car is not ready for sale, " +
                "power do not match the condition";
        return resultTrue;
    }

    private static String checkIfCountFalse(Car car) {
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

    public Car find(String id) {
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
        Car car = find(id);
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

    public void printManufacturerAndCount(final Car car) {
        final Optional<Car> carOptional = Optional.ofNullable(car);
        if (carOptional.isPresent()) {
            System.out.println("Manufacturer: " + carOptional.get().getManufacturer() +
                    " Count: " + carOptional.get().getCount());
        }
    }

    public void printColor(final Car car) {
        final Optional<Car> carOptional = Optional.ofNullable(car);
        final Car car1 = carOptional.orElse(createPassengerCar());
        System.out.println(car1.getColor());
    }

    public void checkCount(final Car car) {
        final Optional<Car> carOptional = Optional.ofNullable(car);
        carOptional.orElseThrow(() -> new UserInputException("Unchecked exception"));
        carOptional.filter(c -> {
                    return c.getCount() > 10;
                })
                .ifPresent(c -> {
                    System.out.println("Manufacturer: " + c.getManufacturer() +
                            " Count: " + c.getCount());
                });
    }

    private static class UserInputException extends RuntimeException {
        private UserInputException(String message) {
            super(message);
        }
    }

    public void printEngineInfo(final Car car) {
        Optional<Car> carOptional = Optional.ofNullable(car);
        Car car1 = carOptional.orElseGet(() -> {
            System.out.println("New car was created");
            return create(getRandomType());
        });
        Optional.of(car1).map(c -> {
                    return c.getEngine();
                })
                .ifPresent(engine -> {
                    System.out.println("Engine Power: " + engine.getPower());
                });
    }

    public void printInfo(final Car car) {
        Optional<Car> carOptional = Optional.ofNullable(car);
        carOptional.ifPresentOrElse(c -> {
                    printCar(carOptional.get());
                },
                () -> {
                    printCar(create(getRandomType()));
                });
    }
}
