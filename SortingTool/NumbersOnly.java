import java.util.Scanner;

public class NumbersOnly {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberNumber = 0;
        int howManyTimes = 0;
        long greatest = Long.MIN_VALUE;
        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();

            numberNumber++;
            if (number > greatest) {
                greatest = number;
                howManyTimes = 1;
            } else if (number == greatest) {
                howManyTimes++;
            }

        }
        System.out.println("Total numbers: " + numberNumber + ".");
        System.out.println("The greatest number: " + greatest + " (" + howManyTimes + " time(s)).");
    }
}
