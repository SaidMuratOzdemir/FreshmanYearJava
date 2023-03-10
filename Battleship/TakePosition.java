package Battleship;

import java.util.Scanner;

public class TakePosition {
    static int firstAbscissa;
    static int secondAbscissa;
    static int firstOrdinate;
    static int secondOrdinate;

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

    }

    public static String isLocated(int[][] array, String string, int shipSize) {
        findLocation(string);

        swap();//swapping helps to avoid repeating code

        if (firstOrdinate == secondOrdinate) { //if ship is horizontal
            if (secondAbscissa - firstAbscissa == shipSize - 1) { //if ship has correct size

                for (int i = 0; i < array[firstOrdinate].length; i++) {//if there is already a ship in that row
                    if (array[firstOrdinate][i] == 2 || array[firstOrdinate][i] == -1) {
                        if (i < firstAbscissa - 1 && i > secondAbscissa - 1) {//if there is a ship between the two abscissas
                            return "Error! You placed it too close to another one. Try again:";
                        }
                    }
                }

                for (int i = firstAbscissa; i <= secondAbscissa; i++) {

                    array[firstOrdinate][i - 1] = 2;//located successfully

                    // assigning -1 to the cells around the ship because we don't want to place ships next to each other
                    if (firstOrdinate - 1 >= 0) {
                        array[firstOrdinate - 1][i - 1] = -1;
                    }
                    if (firstOrdinate + 1 < 10) {
                        array[firstOrdinate + 1][i - 1] = -1;
                    }
                }
                if (firstAbscissa - 1 - 1 >= 0) {
                    array[firstOrdinate][firstAbscissa - 1 - 1] = -1;
                }
                if (secondAbscissa - 1 + 1 < 10) {
                    array[firstOrdinate][secondAbscissa - 1 + 1] = -1;
                }
                return "valid";
            } else {
                return "Error! Wrong length of the Submarine! Try again:";
            }
        } else if (firstAbscissa == secondAbscissa) {
            if (secondOrdinate - firstOrdinate == shipSize - 1) {

                for (int i = 0; i < array.length; i++) {//if there is already a ship in that column
                    if (array[i][firstAbscissa - 1] == 2 || array[i][firstAbscissa - 1] == -1) {
                        if (i > firstOrdinate - 1 && i < secondOrdinate + 1) {//if there is a ship between the two ordinates
                            return "Error! You placed it too close to another one. Try again:";
                        }
                    }
                }

                for (int i = firstOrdinate; i <= secondOrdinate; i++) {
                    array[i][firstAbscissa - 1] = 2;//located successfully

                    // assigning -1 to the cells around the ship because we don't want to place ships next to each other
                    if (firstAbscissa - 1 - 1 >= 0) {
                        array[i][firstAbscissa - 1 - 1] = -1;
                    }
                    if (firstAbscissa - 1 + 1 < 10) {
                        array[i][firstAbscissa - 1 + 1] = -1;
                    }

                }
                if (firstOrdinate - 1 >= 0) {
                    array[firstOrdinate - 1][firstAbscissa - 1] = -1;
                }
                if (secondOrdinate + 1 < 10) {
                    array[secondOrdinate + 1][secondAbscissa - 1] = -1;
                }
                return "valid";
            } else {
                return "Error! Wrong length of the Submarine! Try again:";
            }
        } else {
            return "Error! Wrong ship location! Try again:";
        }
    }


    public static void findLocation(String string) {
        int space = string.indexOf(' ');

        firstOrdinate = string.charAt(0) - 65;
        secondOrdinate = string.charAt(space + 1) - 65;

        if (space == 2) {//if first abscissa is one digit
            firstAbscissa = string.charAt(1) - 48;
            if (string.length() - 1 - space == 2) {//if second abscissa is one digit
                secondAbscissa = string.charAt(4) - 48;
            } else if (string.length() - 1 - space == 3) {//if second abscissa is two digit
                secondAbscissa = (string.charAt(4) - 48) * 10 + string.charAt(5) - 48;
            }
        } else if (space == 3) {//if first abscissa is two digit
            firstAbscissa = Integer.parseInt(string.substring(1, 3));
            if (string.length() - 1 - space == 2) {//if second abscissa is one digit
                secondAbscissa = string.charAt(5) - 48;
            } else if (string.length() - 1 - space == 3) {//if second abscissa is two digit
                secondAbscissa = (string.charAt(5) - 48) * 10 + string.charAt(6) - 48;
            }

        }
    }

    public static void swap() {
        if (firstOrdinate > secondOrdinate) {
            int temp = firstOrdinate;
            firstOrdinate = secondOrdinate;
            secondOrdinate = temp;
        }
        if (firstAbscissa > secondAbscissa) {
            int temp = firstAbscissa;
            firstAbscissa = secondAbscissa;
            secondAbscissa = temp;
        }
    }

    public static void show(int[][] array) {
        System.out.print("\n  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < array.length; i++) {
            System.out.print("\n" + (char) (65 + i));
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] == 0 || array[i][j] == -1) {
                    System.out.print(" ~"); //empty or unknown
                } else if (array[i][j] == 1) {
                    System.out.print(" M"); //missed
                } else if (array[i][j] == 2) {
                    System.out.print(" O"); //your ship
                } else if (array[i][j] == 3) {
                    System.out.print(" X"); //hitted
                }
            }
        }
        System.out.println();
    }
}