package com.lavrentieva.container;

import com.lavrentieva.model.Car;

import java.util.Random;


public class GreenericContainer<T extends Car> {
    private final T t;
    private final Random random = new Random();

    public GreenericContainer(T t) {
        this.t = t;
    }

    public void print(T t) {
        System.out.println(t);
    }

    public void increaseCount(T t) {
        if (t == null) {
            return;
        }
        int oldCount = t.getCount();
        int amountOfChange = getRandomCount();
        t.setCount(oldCount + amountOfChange);
        System.out.println("Car's count has been changed to " + amountOfChange);
    }

    private int getRandomCount() {
        return random.nextInt(100, 300);
    }

    public <S extends Number> void increaseCount(T t, S s) {
        if (t != null && s != null) {
            int oldCount = t.getCount();
            int amountOfChange = s.intValue();
            t.setCount(oldCount + amountOfChange);
            System.out.println("Car's count has been changed to " + amountOfChange);
        }
    }
}
