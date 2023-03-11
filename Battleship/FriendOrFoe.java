package Battleship;
import java.util.Scanner;

class Player {
    public int[][] shipMap;
    public int[][] warMap;
    public String[] shipName;
    public int[] shipSize;

    public Player(int[][] shipMap, int[][] warMap, String[] shipName, int[] shipSize) {
        this.shipMap = shipMap;
        this.warMap = warMap;
        this.shipName = shipName;
        this.shipSize = shipSize;
    }
}

public class FriendOrFoe {
    public static int[] coordinates = new int[4];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] shipName = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};
        int[] shipSize = {5, 4, 3, 3, 2};

        Player[] players = new Player[2];

        players[0] = new Player(new int[10][10], new int[10][10], shipName, shipSize);
        players[1] = new Player(new int[10][10], new int[10][10], shipName, shipSize);

        System.out.println("Player 1, place your ships on the game field");
        locateShips(players[0], shipName, shipSize);

        System.out.println("Press Enter and pass the move to another player");
        scan.nextLine();

        System.out.println("Player 2, place your ships on the game field");
        locateShips(players[1], shipName, shipSize);

        boolean isAnyoneSunk = false;

        while (!isAnyoneSunk) {
            System.out.println("Player 1, it's your turn:");
            extracted(players[0].warMap, players[1].shipMap, scan);
            isAnyoneSunk = isAllSunk(players[1].shipMap) || isAllSunk(players[0].shipMap);
            if (isAnyoneSunk) {
                break;
            }
            System.out.println("Player 2, it's your turn:");
            extracted(players[1].warMap, players[0].shipMap, scan);
            isAnyoneSunk = isAllSunk(players[1].shipMap) || isAllSunk(players[0].shipMap);
        }

    }

    private static void extracted(int[][] warMap,int [][] shipMap, Scanner scan) {
        showMaps(warMap, shipMap);
        String input = scan.nextLine();

        char shotOrdinate = input.charAt(0);
        int shotAbscissa = Integer.parseInt(input.substring(1));

        int whatDidHit = 0;
        while (true){
            if (shotOrdinate < 'A' || shotOrdinate > 'J' || shotAbscissa < 0 || shotAbscissa > 10) {
                System.out.println("Error! You entered the wrong coordinates!");
                System.out.println("Press Enter and pass the move to another player");
                scan.nextLine();
                break;
            }
            if (warMap[shotOrdinate - 65][shotAbscissa - 1] == 1 || warMap[shotOrdinate - 65][shotAbscissa - 1] == 3) {
                show(warMap);
                System.out.println("You hit a ship!");
                System.out.println("You missed!");
                System.out.println("Press Enter and pass the move to another player");
                scan.nextLine();
                break;
            }
        }

        if (shipMap[shotOrdinate - 65][shotAbscissa - 1] > 4) {
            whatDidHit = shipMap[shotOrdinate - 65][shotAbscissa - 1];
            shipMap[shotOrdinate - 65][shotAbscissa - 1] = 3;
            warMap[shotOrdinate - 65][shotAbscissa - 1] = 3;
            show(warMap);
            System.out.println();
            System.out.println("You hit a ship! Try again:");
        } else {
            shipMap[shotOrdinate - 65][shotAbscissa - 1] = 1;
            warMap[shotOrdinate - 65][shotAbscissa - 1] = 1;
            show(warMap);
            System.out.println("You missed.");
            System.out.println("Press Enter and pass the move to another player");
            scan.nextLine();

        }

        boolean isSunk = isSunk(shipMap, whatDidHit);
        boolean isAllSunk = isAllSunk(shipMap);
        if (isAllSunk) {
            System.out.println("You sank the last ship. You won. Congratulations!");
        } else if (!isSunk) {
            System.out.println("You sank a ship! Specify a new target:");
        }
    }

    public static void showMaps(int[][] warMap, int[][] shipMap) {
        show(warMap);
        System.out.println("---------------------");
        show(shipMap);
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

    public static void locateShips(Player player, String[] shipName, int[] shipSize) {

        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < shipName.length; i++) {
            show(player.shipMap);
            System.out.println("Enter the coordinates of the " + shipName[i] + " (" + shipSize[i] + " cells):");
            String input = scan.nextLine();
            while (!isLocated(player.shipMap, input, shipSize[i], i).equals("valid")) {
                System.out.println(isLocated(player.shipMap, input, shipSize[i], i));
                input = scan.nextLine();
            }
            show(player.shipMap);
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
                    System.out.print(" O"); //your ship
                } else if (array[i][j] == 3) {
                    System.out.print(" X"); //hitted
                } else {
                    System.out.print(" ~"); //empty or unknown
                }
            }
        }
        System.out.println();
    }
}
