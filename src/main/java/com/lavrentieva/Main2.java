package com.lavrentieva;

public class Main2 {
    public static void main(String[] args) {
        float side1 = (int) (Math.random() * 100 + 1);
        float side2 = (int) (Math.random() * 100 + 1);
        float side3 = (int) (Math.random() * 100 + 1);
        if (side1 < (side2 + side3) && side2 < (side1 + side3) && side3 < (side1 + side2)) {
            float area = (side1 + side2 + side3) / 2;
            float areaResult;
            areaResult = (float) Math.sqrt(area * (area - side1) * (area - side2) * (area - side3));
            System.out.println("Aria of triangle = " + areaResult);
        } else {
            System.out.println("This is not a triangle");
        }
        System.out.println();

        int random1 = (int) (Math.random() * Integer.MAX_VALUE + 1);
        int random2 = (int) (Math.random() * Integer.MAX_VALUE + 1);
        int random3 = (int) (Math.random() * Integer.MAX_VALUE + 1);
        int minValue = Math.abs(random1 < random2 ? Math.min(random1, random3) : Math.min(random2, random3));
        System.out.println("Min Value is " + minValue);
        System.out.println();

        if (random1 % 2 == 0) {
            System.out.println("Number is even");
        } else {
            System.out.println(("Number is odd"));
        }
        System.out.println();

        int forBinary = (int) (Math.random() * 100 + 1);
        String binary = Integer.toBinaryString(forBinary);
        System.out.println("The binary number is " + binary);
    }
}
