import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number, guess, numberDigitThird, numberDigitSecond, numberDigitFirst, guessDigitThird, guessDigitSecond, guessDigitFirst, incorrectDigit, correctDigit;
        boolean booleanFirst, booleanSecond, booleanThird;
        System.out.print("Enter a number: ");
        number = sc.nextInt();

        while (number < 100 || number > 999) {
            System.out.println("Invalid number, enter a three-digit positive number: ");
            System.out.print("Enter a number: ");
            number = sc.nextInt();
        }

        do {
            System.out.print("Your guess: ");
            guess = sc.nextInt();

            while (guess < 100 || guess > 999) {
                System.out.println("Invalid guess, enter a three-digit positive number: ");
                System.out.print("Your guess: ");
                guess = sc.nextInt();
            }
            // We calculate the digits of the number in loop because we need to change them later.
            numberDigitThird = number % 10;
            numberDigitSecond = (number / 10) % 10;
            numberDigitFirst = number / 100;

            guessDigitThird = guess % 10;
            guessDigitSecond = (guess / 10) % 10;
            guessDigitFirst = guess / 100;

            incorrectDigit = 0;
            correctDigit = 0;

            //checking for same numbers for each digit if it is not returns False for each digit
            if (numberDigitFirst == guessDigitFirst) {
                correctDigit += 1;
                booleanFirst = true;
            } else {
                booleanFirst = false;
            }

            if (numberDigitSecond == guessDigitSecond) {
                correctDigit += 1;
                booleanSecond = true;
            } else {
                booleanSecond = false;
            }

            if (numberDigitThird == guessDigitThird) {
                correctDigit += 1;
                booleanThird = true;
            } else {
                booleanThird = false;
            }

            // booleans are used because the program has to behave differently
            // this section works when first digit is same, second digit is NOT same and third digit is NOT same
            if (booleanFirst && !booleanSecond && !booleanThird) {
                if (numberDigitSecond == guessDigitThird) {
                    incorrectDigit++;
                }
                if (numberDigitThird == guessDigitSecond) {
                    incorrectDigit++;
                }
            }

            // this section works when first digit is NOT same, second digit is same and third digit is NOT same
            if (!booleanFirst && booleanSecond && !booleanThird) {
                if (numberDigitFirst == guessDigitThird) {
                    incorrectDigit++;
                }
                if (numberDigitThird == guessDigitFirst) {
                    incorrectDigit++;
                }
            }

            // this section works when first digit is NOT same, second digit is NOT same and third digit is same
            if (!booleanFirst && !booleanSecond && booleanThird) {
                if (numberDigitFirst == guessDigitSecond) {
                    incorrectDigit++;
                }
                if (numberDigitSecond == guessDigitFirst) {
                    incorrectDigit++;
                }
            }

            // this section works when first digit is same, second digit is same and third digit is NOT same
            if (!booleanFirst && !booleanSecond && !booleanThird) {
                if (guessDigitFirst == numberDigitSecond || guessDigitFirst == numberDigitThird) {
                    incorrectDigit++;

                    if (guessDigitFirst == numberDigitSecond) {
                        numberDigitSecond = -1;
                    } else {
                        numberDigitThird = -1;
                    }

                    guessDigitFirst = -1; // this is for avoiding counting the same digit twice
                }


                if (guessDigitSecond == numberDigitFirst || guessDigitSecond == numberDigitThird) {
                    incorrectDigit++;

                    if (guessDigitSecond == numberDigitFirst) {
                        numberDigitFirst = -1;
                    } else {
                        numberDigitThird = -1;
                    }

                    guessDigitSecond = -1;
                }

                if (guessDigitThird == numberDigitFirst || guessDigitThird == numberDigitSecond) {
                    incorrectDigit++;
                }
            }
            //We don't want to print the result if the user guessed the number
            if (!(booleanFirst && booleanSecond && booleanThird)) {
                System.out.println("Correct place: " + correctDigit);
                System.out.println("Incorrect place: " + incorrectDigit + "\n");
            }


        } while (guess != number);

        System.out.println("You guessed the number. Congrats!");
    }
}
