package Battleship;

import java.util.Scanner;

import static Battleship.TakePosition.locate;
import static Battleship.TakePosition.show;

public class FogOfWar {
    public static void main(String[] args) {
        int[][] map1 = new int[10][10];
        int[][] map2 = new int[10][10];
        String[] shipName = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};
        int[] shipSize = {5, 4, 3, 3, 2};

        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < shipName.length; i++) {
            System.out.println("Enter the coordinates of the " + shipName[i] + " (" + shipSize[i] + " cells):");
            String input = scan.nextLine();
            while (!locate(map1, input, shipSize[i]).equals("valid")) {
                System.out.println(locate(map1, input, shipSize[i]));
                input = scan.nextLine();
            }
            show(map1);
        }

        System.out.println("\nThe game starts!");

        System.out.println("\nTake a shot");
        show(map2);

        boolean game = true;
        while (game) {
            String input = scan.nextLine();

            char shotOrdinate = input.charAt(0);
            int shotAbscissa = Integer.parseInt(input.substring(1));

            if (shotOrdinate < 'A' || shotOrdinate > 'J' || shotAbscissa < 0 || shotAbscissa > 10) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            } else {
                if (map1[shotOrdinate - 65][shotAbscissa - 1] == 2) {
                    map1[shotOrdinate - 65][shotAbscissa - 1] = 3;
                    map2[shotOrdinate - 65][shotAbscissa - 1] = 3;
                    show(map2);
                    System.out.println();
                    System.out.println("You hit a ship!");
                    show(map1);
                } else {
                    map1[shotOrdinate - 65][shotAbscissa - 1] = 1;
                    map2[shotOrdinate - 65][shotAbscissa - 1] = 1;
                    show(map2);
                    System.out.println("You missed!");
                    show(map1);
                }
                game = false;
            }
        }
    }
}
