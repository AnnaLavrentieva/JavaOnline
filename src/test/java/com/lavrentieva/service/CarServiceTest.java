package com.lavrentieva.service;

import com.lavrentieva.model.Color;
import com.lavrentieva.model.PassengerCar;
import com.lavrentieva.model.Truck;
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
    void createAndCheckParametersPassengerCar() {
        final PassengerCar passengerCar = target.createPassengerCar();
        Assertions.assertNotNull(passengerCar);
        Assertions.assertNotNull(passengerCar.getId());
        Assertions.assertNotNull(passengerCar.getEngine());
        Assertions.assertNotNull(passengerCar.getManufacturer());
        Assertions.assertNotNull(passengerCar.getColor());
        Assertions.assertNotNull(passengerCar.getType());
    }

    @Test
    void createAndCheckParametersTruck() {
        final Truck truck = target.createTruck();
        Assertions.assertNotNull(truck);
        Assertions.assertNotNull(truck.getId());
        Assertions.assertNotNull(truck.getEngine());
        Assertions.assertNotNull(truck.getManufacturer());
        Assertions.assertNotNull(truck.getColor());
        Assertions.assertNotNull(truck.getType());
    }

    @Test
    void createWithCountParameterNegativeNumber() {
        final PassengerCar passengerCar = new PassengerCar();
        int count = -1;
        doNothing().when(repository).save(passengerCar);
        target.create(count);
        Mockito.verify(repository, Mockito.never()).save(passengerCar);
    }

    @Test
    void createWithCountParameterZero() {
        final PassengerCar passengerCar = new PassengerCar();
        int count = 0;
        doNothing().when(repository).save(passengerCar);
        target.create(count);
        Mockito.verify(repository, Mockito.never()).save(passengerCar);
    }

    @Test
    void createWithCorrectCountParameter() {
        final PassengerCar passengerCar = new PassengerCar();
        int count = 13;
        target.create(count);
        Assertions.assertNotNull(passengerCar);
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
    void print() {
        final PassengerCar passengerCar = new PassengerCar();
        Assertions.assertDoesNotThrow(() -> target.printPassengerCar(passengerCar));
    }

    @Test
    void getAll() {
        int arrayLength = 1;
        final PassengerCar[] expected = new PassengerCar[arrayLength];
        expected[0] = new PassengerCar();
        Mockito.when(repository.getAll()).thenReturn(expected);
        final PassengerCar[] actual = target.getAll();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    void printAll() {
        Assertions.assertDoesNotThrow(() -> target.printAll());
    }

    @Test
    void findIdIncorrectIdNull() {
        final PassengerCar passengerCar = target.find(null);
        Assertions.assertNull(passengerCar);
    }

    @Test
    void findIdIncorrectIdEmpty() {
        String id = "";
        final PassengerCar passengerCar = target.find(id);
        Assertions.assertNull(passengerCar);
    }

    @Test
    void findNotFound() {
        String id = "666";
        Mockito.when(repository.getById("666")).thenReturn(null);
        final PassengerCar passengerCar = target.find(id);
        Assertions.assertNull(passengerCar);
    }

    @Test
    void findFound() {
        String id = "777";
        PassengerCar expected = new PassengerCar();
        Mockito.when(repository.getById(id)).thenReturn(expected);
        final PassengerCar actual = target.find(id);
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
        PassengerCar passengerCar = new PassengerCar();
        Mockito.when(repository.getById(id)).thenReturn(passengerCar);
        target.changeRandomColor(id);
        Mockito.verify(repository).updateColor(Mockito.anyString(), Mockito.any(Color.class));
    }
}