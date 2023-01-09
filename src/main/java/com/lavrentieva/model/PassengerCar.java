package com.lavrentieva.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;
@Getter
@Setter
@NoArgsConstructor
public class PassengerCar extends Car {
    private int passengerCount;
    private final Random random = new Random();

    public PassengerCar(String manufacturer, Color color, Type type, int passengerCount) {
        super(manufacturer, color, type);
        super.count = restore();
        this.passengerCount = passengerCount;
    }

    @Override
    public int restore() {
        System.out.println("Count was restore");
        return random.nextInt(1, 100);
    }

    @Override
    public String toString() {
        return String.format("%s, %s", super.toString(), "Passenger Count: " + passengerCount);
    }
}
