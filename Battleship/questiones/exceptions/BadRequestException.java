package Battleship.questiones.exceptions;
//Create a class named BadRequestException with the constructor that takes
// String message as an argument and passes the message to the superclass.
// The BadRequestException class must inherit from a checked exception.

// update the class
class BadRequestException extends Exception {
    BadRequestException(String message) {
        super(message);//Constructs a new exception with the specified detail message.
        //source https://docs.oracle.com/javase/7/docs/api/java/lang/Exception.html
    }
}