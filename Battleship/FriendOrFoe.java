package Battleship;

import java.util.Scanner;

class Player {
    public String name;
    public int[][] shipMap;
    public int[][] warMap;
    public String[] shipName;
    public int[] shipSize;

    public Player(String name, int[][] shipMap, int[][] warMap, String[] shipName, int[] shipSize) {
        this.name = name;
        this.shipMap = shipMap;
        this.warMap = warMap;
        this.shipName = shipName;
        this.shipSize = shipSize;
    }
}

public class FriendOrFoe {
    public static int[] coordinates = new int[4];

    public static void main(String[] args) {

        String[] shipName = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};
        int[] shipSize = {5, 4, 3, 3, 2};

        Player[] players = new Player[2];

        players[0] = new Player("Player 1", new int[10][10], new int[10][10], shipName, shipSize);
        players[1] = new Player("Player 2", new int[10][10], new int[10][10], shipName, shipSize);

        locateShips(players[0], shipName, shipSize);
        show(players[0].shipMap);
        pass();

        locateShips(players[1], shipName, shipSize);
        show(players[1].shipMap);
        pass();

        boolean isAnyoneSunk = false;

        while (!isAnyoneSunk) {

            isAnyoneSunk = play(players[0], players[1]);

            if (isAnyoneSunk) {
                break;
            }
            isAnyoneSunk = play(players[1], players[0]);
        }
    }

    public static boolean play(Player player, Player enemy) {
        Scanner scan = new Scanner(System.in);

        showMaps(player.warMap, player.shipMap);
        System.out.println(player.name + ", it's your turn:");

        String input = scan.nextLine();

        char shotOrdinate = input.charAt(0);
        int shotAbscissa = Integer.parseInt(input.substring(1));

        int whatDidHit = 0;

        if (shotOrdinate < 'A' || shotOrdinate > 'J' || shotAbscissa < 0 || shotAbscissa > 10) {
            System.out.println("Error! You entered the wrong coordinates!");
            pass();
            return false;
        }
        if (player.warMap[shotOrdinate - 65][shotAbscissa - 1] == 1 || player.warMap[shotOrdinate - 65][shotAbscissa - 1] == 3) {
            //already hit

            System.out.println("You hit a ship!");
            System.out.println("You missed!");
            //
        }

        if (enemy.shipMap[shotOrdinate - 65][shotAbscissa - 1] > 4) { //successfully hit
            whatDidHit = enemy.shipMap[shotOrdinate - 65][shotAbscissa - 1];

            enemy.shipMap[shotOrdinate - 65][shotAbscissa - 1] = 3;
            player.warMap[shotOrdinate - 65][shotAbscissa - 1] = 3;

            System.out.println();
            System.out.println("You hit a ship!");
            //
        } else {//missed
            enemy.shipMap[shotOrdinate - 65][shotAbscissa - 1] = 1;
            player.warMap[shotOrdinate - 65][shotAbscissa - 1] = 1;

            System.out.println();
            System.out.println("You missed.");
            //
        }
        pass();

        boolean isTheShipSunk = isSunk(enemy.shipMap, whatDidHit);
        boolean isAllSunk = isAllSunk(enemy.shipMap);
        if (isAllSunk) {
            System.out.println("You sank the last ship. You won. Congratulations!");
            return true;
        } else if (!isTheShipSunk) {
            System.out.println("You sank a ship! Specify a new target.");
            pass();
        }
        return false;
    }

    public static void pass() {
        System.out.println("Press Enter and pass the move to another player");
        new Scanner(System.in).nextLine();
    }

    public static void showMaps(int[][] warMap, int[][] shipMap) {
        show(warMap);
        System.out.println("---------------------");
        show(shipMap);
    }

    public static boolean isSunk(int[][] shipMap, int whatDidHit) {
        for (int i = 0; i < shipMap.length; i++) {
            for (int j = 0; j < shipMap[i].length; j++) {
                if ((shipMap[i][j] == whatDidHit)) {//there is still a part of the ship that is not hit
                    return true;
                }
            }
        }
        return false;
    }


    public static void locateShips(Player player, String[] shipName, int[] shipSize) {
        System.out.println(player.name + ", place your ships on the game field");
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < shipName.length; i++) {
            show(player.shipMap);
            System.out.println("Enter the coordinates of the " + shipName[i] + " (" + shipSize[i] + " cells):");
            String input = scan.nextLine();
            while (!isLocated(player.shipMap, input, shipSize[i], i).equals("valid")) {
                System.out.println(isLocated(player.shipMap, input, shipSize[i], i));
                input = scan.nextLine();
            }
        }
    }

    static String isLocated(int[][] array, String string, int shipSize, int shipNumber) {

        findCoordinate(string);

        swap();//swapping helps to avoid repeating code

        if (coordinates[0] == coordinates[1]) { //if ship is horizontal
            if (coordinates[3] - coordinates[2] == shipSize - 1) { //if ship has correct size

                for (int i = 0; i < array[coordinates[0]].length; i++) {
                    if (array[coordinates[0]][i] > 4 || array[coordinates[0]][i] == -1) {//if there is already a ship in that row
                        if (i < coordinates[2] - 1 && i > coordinates[3] - 1) {//if there is a ship between these two abscissas
                            return "Error! You placed it too close to another one. Try again:";
                        }
                    }
                }

                for (int i = coordinates[2]; i <= coordinates[3]; i++) {

                    array[coordinates[0]][i - 1] = shipNumber + 5;//located successfully

                    // assigning -1 to the cells around the ship because we don't want to place ships next to each other
                    if (coordinates[0] - 1 >= 0) {
                        array[coordinates[0] - 1][i - 1] = -1;
                    }
                    if (coordinates[0] + 1 < 10) {
                        array[coordinates[0] + 1][i - 1] = -1;
                    }
                }
                if (coordinates[2] - 1 - 1 >= 0) {
                    array[coordinates[0]][coordinates[2] - 1 - 1] = -1;
                }
                if (coordinates[3] - 1 + 1 < 10) {
                    array[coordinates[0]][coordinates[3] - 1 + 1] = -1;
                }
                return "valid";
            } else {
                return "Error! Wrong length of the Submarine! Try again:";
            }
        } else if (coordinates[2] == coordinates[3]) {
            if (coordinates[1] - coordinates[0] == shipSize - 1) {//size matches

                for (int i = 0; i < array.length; i++) {//if there is already a ship in that column
                    if (array[i][coordinates[2] - 1] > 4 || array[i][coordinates[2] - 1] == -1) {
                        if (i > coordinates[0] - 1 && i < coordinates[1] + 1) {//if there is a ship between the two ordinates
                            return "Error! You placed it too close to another one. Try again:";
                        }
                    }
                }

                for (int i = coordinates[0]; i <= coordinates[1]; i++) {
                    array[i][coordinates[2] - 1] = shipNumber + 5;//located successfully

                    // assigning -1 to the cells around the ship because we don't want to place ships next to each other
                    if (coordinates[2] - 1 - 1 >= 0) {
                        array[i][coordinates[2] - 1 - 1] = -1;
                    }
                    if (coordinates[2] - 1 + 1 < 10) {
                        array[i][coordinates[2] - 1 + 1] = -1;
                    }

                }
                if (coordinates[0] - 1 >= 0) {
                    array[coordinates[0] - 1][coordinates[2] - 1] = -1;
                }
                if (coordinates[1] + 1 < 10) {
                    array[coordinates[1] + 1][coordinates[3] - 1] = -1;
                }
                return "valid";
            } else {
                return "Error! Wrong length of the Submarine! Try again:";
            }
        } else {
            return "Error! Wrong ship location! Try again:";
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


    public static void findCoordinate(String string) {
        int space = string.indexOf(' ');

        coordinates[0] = string.charAt(0) - 65;
        coordinates[1] = string.charAt(space + 1) - 65;

        if (space == 2) {//if first abscissa is one digit
            coordinates[2] = string.charAt(1) - 48;
            if (string.length() - 1 - space == 2) {//if second abscissa is one digit
                coordinates[3] = string.charAt(4) - 48;
            } else if (string.length() - 1 - space == 3) {//if second abscissa is two digit
                coordinates[3] = (string.charAt(4) - 48) * 10 + string.charAt(5) - 48;
            }
        } else if (space == 3) {//if first abscissa is two digit
            coordinates[2] = Integer.parseInt(string.substring(1, 3));
            if (string.length() - 1 - space == 2) {//if second abscissa is one digit
                coordinates[3] = string.charAt(5) - 48;
            } else if (string.length() - 1 - space == 3) {//if second abscissa is two digit
                coordinates[3] = (string.charAt(5) - 48) * 10 + string.charAt(6) - 48;
            }

        }
    }

    public static void swap() {
        if (coordinates[0] > coordinates[1]) {
            int temp = coordinates[0];
            coordinates[0] = coordinates[1];
            coordinates[1] = temp;
        }
        if (coordinates[2] > coordinates[3]) {
            int temp = coordinates[2];
            coordinates[2] = coordinates[3];
            coordinates[3] = temp;
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
                    System.out.print(" O"); //a ship
                } else if (array[i][j] == 3) {
                    System.out.print(" X"); //hitted
                } else {
                    System.out.print(" ~"); //empty or unknown
                }
            }
        }
        System.out.println("\n");
    }
}
