package com.lavrentieva.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PassengerCar extends Car {
    private int passengerCount;

    public PassengerCar(String manufacturer, Color color, Type type, int passengerCount) {
        super(manufacturer, color, type);
        super.count = restore();
        this.passengerCount = passengerCount;
    }

    @Override
    public int restore() {
        System.out.println("Count was restore");
        return 100;
    }

    @Override
    public String toString() {
        return String.format("%s, %s %n", super.toString(), "Passenger Count: " + passengerCount);
    }
}
