package com.lavrentieva;

public class Main {
    public static void main(String[] args) {

        String lastName = "Lavrentieva";
        String firstName = "Anna";
        System.out.println(firstName + " " + lastName);
        System.out.println("Anna Lavrentieva");
        System.out.println();

        for (int i = 0, y = 5; i <= 10; i++, y += 2) {
            System.out.println("Step " + i + ", Value " + y);
        }
        System.out.println();

        int i = 0;
        int y = 5;
        do {
            System.out.println("Step " + i + ", Value " + y);
            i++;
            y += 2;
        }
        while (i <= 10);
        System.out.println();

        i = 0;
        y = 5;
        while (i <= 10) {
            System.out.println("Step " + i + ", Value " + y);
            i++;
            y += 2;
        }
        System.out.println();

        for (i = 0; i <= 10; i++) {
            if (i == 3) {
                continue;
            }
            if (i == 6) {
                break;
            }
            System.out.println("Step " + i);
        }
    }
}
