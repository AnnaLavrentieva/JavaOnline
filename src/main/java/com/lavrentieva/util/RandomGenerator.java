package com.lavrentieva.util;

import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor
public class RandomGenerator {
    private final Random randomForGenerate = new Random();

    public int randomInteger() {
        int randomInteger = randomForGenerate.nextInt(0, 10);
        if (randomInteger < 0) {
            return 0;
        } else if (randomInteger > 10) {
            return 10;
        }
        return randomInteger;
    }
}
