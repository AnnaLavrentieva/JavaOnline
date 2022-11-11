package com.lavrentieva;

import java.util.Arrays;
import java.util.Random;

public class Main4 {

    public static void main(String[] args) {
exs1();
exs2();
exs3();
exs4();
    }

    public static void exs1() {

        int[] numbers = new int[12];
        int firstRand = -15;
        int lastRand = 15;
        int maxValue = firstRand;
        Random random = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(firstRand, lastRand);
        }
        System.out.println(Arrays.toString(numbers));

        for (int number : numbers) {
            if (number > maxValue) {
                maxValue = number;
            }
        }
        System.out.println("Max value is " + maxValue);

        for (int i = numbers.length - 1; ; i--) {
            if (numbers[i] == maxValue) {
                System.out.println("Last index is " + i);
                break;
            }
        }
        System.out.println();
    }

    public static void exs2() {
        int[] numbers = new int[8];
        int firstRand = 1;
        int lastRand = 10;
        Random random = new Random();

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(firstRand, lastRand);
        }
        System.out.println(Arrays.toString(numbers));

        for (int i = 0; i < numbers.length; i++) {
            if (Math.abs(i % 2) != 0) {
                numbers[i] = 0;
            }
        }
        System.out.println(Arrays.toString(numbers));
        System.out.println();
    }

    public static void exs3() {
        int[] numbers = new int[4];
        int firstRand = 10;
        int lastRand = 99;
        int minValue = firstRand - 1;
        Random random = new Random();
        boolean isGood = true;

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(firstRand, lastRand);
        }
        System.out.println(Arrays.toString(numbers));

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > minValue) {
                minValue = numbers[i];
            } else {
                isGood = false;
                break;
            }
        }
        if (isGood == true) {
            System.out.println("The array is a strictly increasing sequence");
        } else {
            System.out.println("The array is NOT a strictly increasing sequence");
        }
        System.out.println();
    }

    public static void exs4() {
        int length = 5;
        int[] numbersFirst = new int[length];
        int[] numbersSecond = new int[length];
        int firstRand = 0;
        int lastRand = 5;
        int sumFirst = 0;
        int sumSecond = 0;
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            numbersFirst[i] = random.nextInt(firstRand, lastRand);
            numbersSecond[i] = random.nextInt(firstRand, lastRand);
            sumFirst = sumFirst + numbersFirst[i];
            sumSecond = sumSecond + numbersSecond[i];
        }
        System.out.println("First array is " + Arrays.toString(numbersFirst));
        System.out.println("Second array is " + Arrays.toString(numbersSecond));

        sumFirst = sumFirst / numbersFirst.length;
        sumSecond = sumSecond / numbersSecond.length;

        if (sumFirst > sumSecond) {
            System.out.println("The average arithmetic of First array is bigger");
        } else if (sumFirst == sumSecond) {
            System.out.println("The average arithmetic of both arrays is equal");
        } else {
            System.out.println("The average arithmetic of Second array is bigger");
        }
    }
}


