package com.lavrentieva.container;

import com.lavrentieva.model.Car;

import java.util.Comparator;

public class CarComparator implements Comparator <Car> {
    @Override
    public int compare(Car o1, Car o2) {
        return Integer.compare(o1.getCount(),o2.getCount());
    }
}
