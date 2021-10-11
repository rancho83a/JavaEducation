package SquareRoot;

import java.util.Scanner;

public class Squareroot {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double res;

        try{
            System.out.println(squareRoot(Double.parseDouble(scan.nextLine())));

        }  catch (IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        } finally {
            System.out.println("Good bye");
        }
    }

    private static double squareRoot(double number) {
        if(number<0){
            throw new IllegalArgumentException("Invalid number");
        }
        return Math.sqrt(number);
    }
}
