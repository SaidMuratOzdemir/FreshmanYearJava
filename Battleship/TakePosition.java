package Battleship;

import java.util.Scanner;

public class TakePosition {
    public static void main(String[] args){
     int[][] map = new int[10][10];
     String[] shipName = {"Aircraft Carrier","Battleship","Submarine","Cruiser","Destroyer"};
     int[] shipSize = {5,4,3,3,2};
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < shipName.length; i++) {
            System.out.println("Enter the coordinates of the "+shipName[i]+" ("+shipSize[i]+" cells):");
            String input = scan.nextLine();
            while (!locate(map,input,shipSize[i]).equals("valid")){
                System.out.println(locate(map,input,shipSize[i]));
                input = scan.nextLine();
            }
            show(map);
        }

    }
    static String locate(int[][] array,String string, int shipSize){
        //TODO: check if that position is already taken
        int space = string.indexOf(' ');

        int firstOrdinate = string.charAt(0);
        int secondOrdinate = string.charAt(space + 1);
        //TODO: if statement for checking if ordinates are char

        int firstAbscissa = -999;//foo might not be initialized
        int secondAbscissa = -999;//foo might not be initialized

        if(space==2){//if first abscissa is one digit
            firstAbscissa = string.charAt(1) - 48;
            if(string.length()-1-space==2){//if second abscissa is one digit
                secondAbscissa = string.charAt(4) - 48;
            }
            else if (string.length()-1-space==3) {//if second abscissa is two digit
                secondAbscissa = (string.charAt(4) - 48) * 10 + string.charAt(5) - 48;
            }
        }
        else if (space==3) {//if first abscissa is two digit
            firstAbscissa = Integer.parseInt(string.substring(1,3));
            if(string.length()-1-space==2){//if second abscissa is one digit
                secondAbscissa = string.charAt(5) - 48;
            }
            else if (string.length()-1-space==3) {//if second abscissa is two digit
                secondAbscissa = (string.charAt(5) - 48) * 10 + string.charAt(6) - 48;
            }

        }

        //TODO: if statement for checking if abscissas are number
        if (firstOrdinate>secondOrdinate){
            int temp = firstOrdinate;
            firstOrdinate = secondOrdinate;
            secondOrdinate = temp;
        }
        if (firstAbscissa>secondAbscissa){
            int temp = firstAbscissa;
            firstAbscissa = secondAbscissa;
            secondAbscissa = temp;
        }


        if(firstOrdinate==secondOrdinate){
            if(secondAbscissa-firstAbscissa==shipSize-1){
                for (int i = firstAbscissa; i <= secondAbscissa; i++) {
                    array[firstOrdinate-65][i-1] = 2;
                }
                return "valid";
            }
            else{
                return "Error! Wrong length of the Submarine! Try again:";
            }
        }
        else if(firstAbscissa==secondAbscissa){
            if(secondOrdinate-firstOrdinate==shipSize-1){
                for (int i = firstOrdinate; i <= secondOrdinate; i++) {
                    array[i-65][firstAbscissa-1] = 2;//success
                }
                return "valid";
            }
            else {
                return "Error! Wrong length of the Submarine! Try again:";
            }
        }
        else {
            return "Error! Wrong ship location! Try again:";
        }
    }

    static void show(int[][] array){
        System.out.print("\n  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < array.length; i++) {
            System.out.print("\n"+ (char)(65+i));
            for (int j = 0; j < array.length; j++) {
                if(array[i][j] == 0){
                    System.out.print(" ~"); //empty or unknown
                }
                else if(array[i][j] == 1) {
                    System.out.print(" M"); //missed
                }
                else if(array[i][j] == 2) {
                    System.out.print(" O"); //your ship
                }
                else if(array[i][j] == 3) {
                    System.out.print(" X"); //hitted
                }
            }
        }
        System.out.println();
    }
}
