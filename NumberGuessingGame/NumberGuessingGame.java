import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number = getNumber(input);
        int guess = getGuess(input);
        while (guess != number) {
            int correct = getCorrectDigit(number, guess);
            int incorrect = getIncorrectDigit(number, guess);
            System.out.println("Correct place: " + correct);
            System.out.println("Incorrect place: " + incorrect);
            guess = getGuess(input);
        }
        System.out.println("You guessed the number. Congrats!");
    }

    public static int getNumber(Scanner input) {
        System.out.print("Enter a number: ");
        int number = input.nextInt();
        while (number < 100 || number > 999) {
            System.out.println("Invalid number, enter a three-digit positive number: ");
            System.out.print("Enter a number: ");
            number = input.nextInt();
        }
        return number;
    }

    public static int getGuess(Scanner input) {
        System.out.print("Your guess: ");
        int guess = input.nextInt();
        while (guess < 100 || guess > 999) {
            System.out.println("Invalid guess, enter a three-digit positive number: ");
            System.out.print("Your guess: ");
            guess = input.nextInt();
        }
        return guess;
    }

    public static int getCorrectDigit(int number, int guess) {
        int correct = 0;
        if (number % 10 == guess % 10) correct++;
        if ((number / 10) % 10 == (guess / 10) % 10) correct++;
        if (number / 100 == guess / 100) correct++;
        return correct;
    }

    public static int getIncorrectDigit(int number, int guess) {
        int incorrect = 0;
        int[] num = {number % 10, (number / 10) % 10, number / 100};
        int[] gue = {guess % 10, (guess / 10) % 10, guess / 100};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != j && num[i] == gue[j]) {
                    incorrect++;
                    break;
                }
            }
        }
        return incorrect;
    }
}
