package Battleship;

import java.util.Scanner;


public class EndOfTheWar {
    static int firstAbscissa;
    static int secondAbscissa;
    static int firstOrdinate;
    static int secondOrdinate;

    public static void main(String[] args) {
        int[][] shipMap = new int[10][10];
        int[][] warMap = new int[10][10];

        String[] shipName = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};
        int[] shipSize = {5, 4, 3, 3, 2};

        Scanner scan = new Scanner(System.in);

        locateShips(shipMap, shipName, shipSize, scan);

        System.out.println("\nThe game starts!");
        System.out.println("\nTake a shot");
        show(warMap);

        boolean isAllSunk = false;

        while (!isAllSunk) {
            String input = scan.nextLine();

            char shotOrdinate = input.charAt(0);
            int shotAbscissa = Integer.parseInt(input.substring(1));

            int whatDidHit = 0;
            if (shotOrdinate < 'A' || shotOrdinate > 'J' || shotAbscissa < 0 || shotAbscissa > 10) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                continue;
            }
            if (warMap[shotOrdinate - 65][shotAbscissa - 1] == 1 || warMap[shotOrdinate - 65][shotAbscissa - 1] == 3) {
                show(warMap);
                System.out.println("You hit a ship!");
                System.out.println("You missed!");
                continue;
            }

            if (shipMap[shotOrdinate - 65][shotAbscissa - 1] > 4) {
                whatDidHit = shipMap[shotOrdinate - 65][shotAbscissa - 1];
                shipMap[shotOrdinate - 65][shotAbscissa - 1] = 3;
                warMap[shotOrdinate - 65][shotAbscissa - 1] = 3;
                show(warMap);
                System.out.println();
                System.out.println("You hit a ship! Try again:");
            } else{
                shipMap[shotOrdinate - 65][shotAbscissa - 1] = 1;
                warMap[shotOrdinate - 65][shotAbscissa - 1] = 1;
                show(warMap);
                System.out.println("You missed. Try again:");

            }

            boolean isSunk = isSunk(shipMap, whatDidHit);
            isAllSunk = isAllSunk(shipMap);
            if (isAllSunk) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            } else
            if (!isSunk) {
                System.out.println("You sank a ship! Specify a new target:");
            }
        }

    }

    public static boolean isSunk(int[][] shipMap, int whatDidHit) {
        boolean isSunk = false;
        boolean loopControl = true;
        for (int i = 0; i < shipMap.length && loopControl; i++) {
            for (int j = 0; j < shipMap[i].length & loopControl; j++) {
                if ((shipMap[i][j] == whatDidHit)) {//there is still a part of the ship that is not hit
                    isSunk = true;
                    loopControl = false;
                }
            }
        }
        return isSunk;
    }

    public static void locateShips(int[][] shipMap, String[] shipName, int[] shipSize, Scanner scan) {
        for (int i = 0; i < shipName.length; i++) {
            System.out.println("Enter the coordinates of the " + shipName[i] + " (" + shipSize[i] + " cells):");
            String input = scan.nextLine();
            while (!isLocated(shipMap, input, shipSize[i], i).equals("valid")) {
                System.out.println(isLocated(shipMap, input, shipSize[i], i));
                input = scan.nextLine();
            }
            show(shipMap);
        }
    }

    public static boolean isAllSunk(int[][] shipMap) {
        for (int i = 0; i < shipMap.length; i++) {
            for (int j = 0; j < shipMap[i].length; j++) {
                if (shipMap[i][j] > 4) {
                    return false;
                }
            }
        }
        return true;
    }

    static String isLocated(int[][] array, String string, int shipSize, int shipNumber) {
        findLocation(string);

        swap();//swapping helps to avoid repeating code

        if (firstOrdinate == secondOrdinate) { //if ship is horizontal
            if (secondAbscissa - firstAbscissa == shipSize - 1) { //if ship has correct size

                for (int i = 0; i < array[firstOrdinate].length; i++) {
                    if (array[firstOrdinate][i] > 4 || array[firstOrdinate][i] == -1) {//if there is already a ship in that row
                        if (i < firstAbscissa - 1 && i > secondAbscissa - 1) {//if there is a ship between these two abscissas
                            return "Error! You placed it too close to another one. Try again:";
                        }
                    }
                }

                for (int i = firstAbscissa; i <= secondAbscissa; i++) {

                    array[firstOrdinate][i - 1] = shipNumber + 5;//located successfully

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
            if (secondOrdinate - firstOrdinate == shipSize - 1) {//size matches

                for (int i = 0; i < array.length; i++) {//if there is already a ship in that column
                    if (array[i][firstAbscissa - 1] > 4 || array[i][firstAbscissa - 1] == -1) {
                        if (i > firstOrdinate - 1 && i < secondOrdinate + 1) {//if there is a ship between the two ordinates
                            return "Error! You placed it too close to another one. Try again:";
                        }
                    }
                }

                for (int i = firstOrdinate; i <= secondOrdinate; i++) {
                    array[i][firstAbscissa - 1] = shipNumber + 5;//located successfully

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
                if (array[i][j] == 1) {
                    System.out.print(" M"); //missed
                } else if (array[i][j] > 4) {
                    System.out.print(" O"); //your ship
                } else if (array[i][j] == 3) {
                    System.out.print(" X"); //hitted
                }else{
                    System.out.print(" ~"); //empty or unknown
                }
            }
        }
        System.out.println();
    }
}
