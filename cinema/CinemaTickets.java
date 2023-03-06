package cinema;

import java.util.Scanner;

public class CinemaTickets {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int a, b, c, d;
        System.out.println("Enter the number of rows:");
        a = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        b = scan.nextInt();

        boolean[][] array = new boolean[a][b];
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= b; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < a; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < b; j++) {
                System.out.print("S ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Enter a row number:");
        c = scan.nextInt();
        System.out.println("Enter a seat number in that row:");
        d = scan.nextInt();
        array[c - 1][d - 1] = true;
        System.out.println();
        System.out.println("Ticket price:");
        if (a * b <= 60) {
            System.out.println("$10");
        } else {
            if (c <= a / 2) {
                System.out.println("$10");
            } else {
                System.out.println("$8");
            }
        }
        System.out.println();
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= b; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < a; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < b; j++) {
                System.out.print(array[i][j] ? "B " : "S ");
            }
            System.out.println();
        }

    }
}