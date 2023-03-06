package cinema;

import java.util.Scanner;

public class CinemaFinal {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int a, b, c;
        System.out.println("Enter the number of rows:");
        a = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        b = scan.nextInt();

        boolean[][] array = new boolean[a][b];

        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            c = scan.nextInt();
            if (c == 1) {
                show(array);
            } else if (c == 2) {
                buy(array);
            } else if (c == 3) {
                stat(array);
            }
        } while (c != 0);
    }

    public static void show(boolean[][] array) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= array[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < array.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j]) {
                    System.out.print("B ");
                } else {
                    System.out.print("S ");
                }
            }
            System.out.println();
        }
    }

    public static void buy(boolean[][] array) {
        int row, seat;
        do {
            System.out.println("Enter a row number:");
            row = scan.nextInt();
            System.out.println("Enter a seat number in that row:");
            seat = scan.nextInt();
            if (row > array.length || seat > array[0].length) {
                System.out.println("Wrong input!");
            } else if (array[row - 1][seat - 1]) {
                System.out.println("That ticket has already been purchased!");
            }
        } while (row > array.length || seat > array[0].length || array[row - 1][seat - 1]);
        array[row - 1][seat - 1] = true;
        int price = 0;
        if (array.length * array[0].length <= 60) {
            price = 10;
        } else {
            if (row <= array.length / 2) {
                price = 10;
            } else {
                price = 8;
            }
        }
        System.out.println("Ticket price: $" + price);
    }

    public static void stat(boolean[][] array) {
        int currentIncome = 0;
        int totalIncome = 0;
        int numberOfPurchasedTickets = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j]) {
                    numberOfPurchasedTickets++;
                    if (array.length * array[0].length <= 60) {
                        currentIncome += 10;
                    } else {
                        if (i < array.length / 2) {
                            currentIncome += 10;
                        } else {
                            currentIncome += 8;
                        }
                    }
                }
            }
        }
        if (array.length * array[0].length <= 60) {
            totalIncome = array.length * array[0].length * 10;
        } else {
            totalIncome = array.length / 2 * array[0].length * 10 + (array.length - array.length / 2) * array[0].length * 8;
        }
        System.out.println("Number of purchased tickets: " + numberOfPurchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", (double) numberOfPurchasedTickets / (array.length * array[0].length) * 100);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }
}
