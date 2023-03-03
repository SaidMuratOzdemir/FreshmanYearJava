import java.util.Scanner;

public class NumberDigitSeparator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a valid number: ");
        int input = scan.nextInt();
        int digitController = 1;
        int backup = input;

        while (backup > 0) {
            digitController *= 10;
            backup = backup / 10;
        }

        while (digitController > 0) {
            int digit = input / digitController;
            if (digit > 0) {
                System.out.print("(" + digit + " * " + digitController + ")");
                input = input % digitController;
                if (input > 0) {
                    System.out.print(" + ");
                }
            }
            digitController /= 10;
        }

    }
}

