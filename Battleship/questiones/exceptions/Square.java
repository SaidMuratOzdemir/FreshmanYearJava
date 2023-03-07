package Battleship.questiones.exceptions;

import java.util.Scanner;

class Square {
    //You have a class named Square with a single constructor, which takes only one argument: a .
    // If the argument is positive, the constructor assigns it to the field a or throws a SquareSizeException otherwise.
    //
    //Here is what you need to do:
    //
    //Finish the constructor implementation
    //Handle a custom exception using a try-catch block
    int a;

    public Square(int a) throws SquareSizeException {
        if (a > 0) this.a = a;
        //put you code here
        else{
            throw new SquareSizeException("zero or negative size");
        };

    }
}

class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int a = scn.nextInt();
        //put your code here
        try {
            Square square = new Square(a);
        }
        catch (SquareSizeException e){
            System.out.println(e.getMessage());
        }

    }
}

class SquareSizeException extends Exception {
    public SquareSizeException(String message) {
        super(message);
    }
}