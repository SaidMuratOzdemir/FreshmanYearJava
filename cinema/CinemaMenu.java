package cinema;
import java.util.Scanner;

public class CinemaMenu {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a,b,c;
        System.out.println("Enter the number of rows:");
        a = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        b  = scan.nextInt();

        boolean[][] array = new boolean[a][b];

        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("0. Exit");
            c = scan.nextInt();
            System.out.println();
            if (c == 1) {
                show(array);
            } else if (c == 2) {
                buy(array);
            }
        } while (c != 0);
    }

    public static void show(boolean[][] array) {
        System.out.println("Cinema:");
        for (int i = 1; i <= array[1].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < array.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < array[1].length; j++) {
                System.out.print(array[i][j] ? "B " : "S ");
            }
            System.out.println();
        }
    }
    public static void buy(boolean[][] array) {
        System.out.println("Enter a row number:");
        int row = scan.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seat = scan.nextInt();
        System.out.println();
        System.out.println("Ticket price:");
        if (array.length * array[1].length <= 60) {
            System.out.println("$10");
        } else {
            if (row <= array.length / 2) {
                System.out.println("$10");
            } else {
                System.out.println("$8");
            }
        }
        System.out.println();
        array[row - 1][seat - 1] = true;
    }
}
