package com.lavrentieva;

public class Main3 {
    public static void main(String[] args) {
        String line = ("Hello World!");
        System.out.println("First symbol: " + line.charAt(0));
        System.out.println("Last symbol: " + line.substring(line.length() - 1));
        System.out.println();

        StringBuilder sentenceV = new StringBuilder();
        sentenceV.append("Java Exercise");
        System.out.println(sentenceV.substring(sentenceV.length() - 2).equals("se"));
        System.out.println();

        String sek = "Stephen Edwin King";
        StringBuilder underStr = new StringBuilder();
        underStr.append("Walter Winchell");
        System.out.println(sek.contains(underStr));
        System.out.println();

        String compStr = "stephen edwin king";
        if (sek.compareToIgnoreCase(compStr) == 0) {
            System.out.println("Strings are equal");
        } else {
            System.out.println("Strings are not equal");
        }
        System.out.println();

        StringBuilder lineTwo = new StringBuilder();
        lineTwo.append("Orange is also my favorite color");
        String contLine = "Red";
        System.out.println(lineTwo.substring(0, contLine.length()).equals(contLine));
        System.out.println();
    }
}
