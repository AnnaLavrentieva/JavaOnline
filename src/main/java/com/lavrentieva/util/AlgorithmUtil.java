package com.lavrentieva.util;

import com.lavrentieva.model.Car;


public class AlgorithmUtil {

    private AlgorithmUtil() {
    }

    public static void bubbleSort(final Car[] cars) {
        for (int i = cars.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (compareCar(cars[j], cars[j + 1]) > 0) {
                    Car temporary = cars[j];
                    cars[j] = cars[j + 1];
                    cars[j + 1] = temporary;
                }
            }
        }
    }

    public static Car binarySearch(Car car, Car[] cars) {
        int firstIndex = 0;
        int lastIndex = cars.length - 1;
        while (firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            if (car.getId().equals(cars[middleIndex].getId())) {
                return cars[middleIndex];
            } else if (compareCar(car, cars[middleIndex]) > 0) {
                firstIndex = middleIndex + 1;
            } else {
                lastIndex = middleIndex - 1;
            }
        }
        return null;
    }

    public static int compareCar(final Car first, final Car second) {
        return first.getId().compareTo(second.getId());
    }
}
