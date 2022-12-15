package com.lavrentieva.service;

import com.lavrentieva.model.*;
import com.lavrentieva.repository.CarArrayRepository;
import com.lavrentieva.util.RandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.doNothing;

class CarServiceTest {
    private CarService target;
    private CarArrayRepository repository;
    private RandomGenerator randomGenerator;

    @BeforeEach
    void setUpMockRepository() {
        repository = Mockito.mock(CarArrayRepository.class);
        target = new CarService(repository);
        randomGenerator = Mockito.mock(RandomGenerator.class);
    }

    @Test
    void createPassengersCar() {
        final Car car = target.create(Type.CAR);
        Assertions.assertNotNull(car);
        Assertions.assertNotNull(car.getId());
        Assertions.assertNotNull(car.getEngine());
        Assertions.assertNotNull(car.getEngine());
        Assertions.assertNotNull(car.getManufacturer());
        Assertions.assertNotNull(car.getColor());
        Assertions.assertNotNull(car.getType());
    }

    @Test
    void createTRUCK() {
        final Car car = target.create(Type.TRUCK);
        Assertions.assertNotNull(car);
        Assertions.assertNotNull(car.getId());
        Assertions.assertNotNull(car.getEngine());
        Assertions.assertNotNull(car.getEngine());
        Assertions.assertNotNull(car.getManufacturer());
        Assertions.assertNotNull(car.getColor());
        Assertions.assertNotNull(car.getType());
    }

    @Test
    void carsDifferentTypesNotEquals() {
        final Car car = target.create(Type.CAR);
        final Car car1 = target.create(Type.TRUCK);
        boolean expected = false;
        boolean actual = target.carEquals(car, car1);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void carsOneTypeNotEquals() {
        final Car car = target.create(Type.CAR);
        final Car car1 = target.create(Type.CAR);
        boolean expected = false;
        boolean actual = target.carEquals(car, car1);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void carEquals() {
        final Car car = target.create(Type.CAR);
        boolean expected = true;
        boolean actual = target.carEquals(car, car);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void createWithCountParameterNegativeNumber() {
        final Car car = new PassengerCar();
        int count = -1;
        doNothing().when(repository).save(car);
        target.create(count);
        Mockito.verify(repository, Mockito.never()).save(car);
    }

    @Test
    void createWithCountParameterZero() {
        final Car car = new PassengerCar();
        int count = 0;
        doNothing().when(repository).save(car);
        target.create(count);
        Mockito.verify(repository, Mockito.never()).save(car);
    }

    @Test
    void createWithCorrectCountParameter() {
        final Car car = new PassengerCar();
        int count = 13;
        target.create(count);
        Assertions.assertNotNull(car);
    }

    @Test
    void RandomAmountZero() {
        int expected = -1;
        int random = 0;
        Mockito.when(randomGenerator.randomInteger()).thenReturn(random);
        int actual = target.createCarFromRandomAmount(randomGenerator);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void createCarFromRandomAmountZero() {
        int random = 0;
        Mockito.when(randomGenerator.randomInteger()).thenReturn(random);
        target.createCarFromRandomAmount(randomGenerator);
        Mockito.verifyNoInteractions(repository);
    }

    @Test
    void RandomAmountCorrect() {
        int expected = 10;
        int random = expected;
        Mockito.when(randomGenerator.randomInteger()).thenReturn(random);
        int actual = target.createCarFromRandomAmount(randomGenerator);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void printCar() {
        final Car car = new PassengerCar();
        Assertions.assertDoesNotThrow(() -> target.printCar(car));
    }

    @Test
    void getAll() {
        int arrayLength = 1;
        final Car[] expected = new PassengerCar[arrayLength];
        expected[0] = new PassengerCar();
        Mockito.when(repository.getAll()).thenReturn(expected);
        final Car[] actual = target.getAll();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    void printAll() {
        Assertions.assertDoesNotThrow(() -> target.printAll());
    }

    @Test
    void findIdIncorrectIdNull() {
        final Car car = target.find(null);
        Assertions.assertNull(car);
    }

    @Test
    void findIdIncorrectIdEmpty() {
        String id = "";
        final Car car = target.find(id);
        Assertions.assertNull(car);
    }

    @Test
    void findNotFound() {
        String id = "666";
        Mockito.when(repository.getById("666")).thenReturn(null);
        final Car car = target.find(id);
        Assertions.assertNull(car);
    }

    @Test
    void findFound() {
        String id = "777";
        Car expected = new PassengerCar();
        Mockito.when(repository.getById(id)).thenReturn(expected);
        final Car actual = target.find(id);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteIfIdNull() {
        target.delete(null);
        Mockito.verify(repository, Mockito.never()).delete(Mockito.anyString());
    }

    @Test
    void deleteIfIdIsEmpty() {
        target.delete("");
        Mockito.verify(repository, Mockito.never()).delete(Mockito.anyString());
    }

    @Test
    void delete() {
        final String id = "666";
        target.delete(id);
        Mockito.verify(repository).delete(id);
    }

    @Test
    void changeRandomColorIfIdNull() {
        target.changeRandomColor(null);
        Mockito.verify(repository, Mockito.never()).updateColor(Mockito.anyString(),
                Mockito.any(Color.class));
    }

    @Test
    void changeRandomColorIfIdIsEmpty() {
        target.changeRandomColor("");
        Mockito.verify(repository, Mockito.never()).updateColor(Mockito.anyString(),
                Mockito.any());
    }

    @Test
    void changeRandomColor() {
        String id = "777";
        Car car = new PassengerCar();
        Mockito.when(repository.getById(id)).thenReturn(car);
        target.changeRandomColor(id);
        Mockito.verify(repository).updateColor(Mockito.anyString(), Mockito.any(Color.class));
    }
}