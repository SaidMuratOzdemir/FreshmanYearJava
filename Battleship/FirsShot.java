package Battleship;

import static Battleship.TakePosition.*;

import java.util.Scanner;

public class FirsShot {
    public static void main(String[] args) {
        int[][] map = new int[10][10];
        String[] shipName = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};
        int[] shipSize = {5, 4, 3, 3, 2};

        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < shipName.length; i++) {
            System.out.println("Enter the coordinates of the " + shipName[i] + " (" + shipSize[i] + " cells):");
            String input = scan.nextLine();
            while (!isLocated(map, input, shipSize[i]).equals("valid")) {
                System.out.println(isLocated(map, input, shipSize[i]));
                input = scan.nextLine();
            }
            show(map);
        }

        System.out.println("\nThe game starts!");

        System.out.println("\nTake a shot");
        show(map);

        boolean game = true;
        while (game) {
            String input = scan.nextLine();

            char shotOrdinate = input.charAt(0);
            int shotAbscissa = Integer.parseInt(input.substring(1));

            if (shotOrdinate < 'A' || shotOrdinate > 'J' || shotAbscissa < 0 || shotAbscissa > 10) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            } else {
                if (map[shotOrdinate - 65][shotAbscissa-1] == 2) {
                    map[shotOrdinate - 65][shotAbscissa-1] = 3;
                    show(map);
                    System.out.println();
                    System.out.println("You hit a ship!");
                } else {

                    map[shotOrdinate - 65][shotAbscissa-1] = 1;
                    show(map);
                    System.out.println("You missed!");
                }
                game = false;
            }
        }
    }
}
