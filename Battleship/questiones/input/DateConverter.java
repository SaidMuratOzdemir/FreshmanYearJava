package Battleship.questiones.input;

import java.util.Scanner;

public class DateConverter {
    public static void main(String[] args){
        String str = new Scanner(System.in).nextLine();
        String[] array = str.split("-");
        System.out.println(array[1] + "/" + array[2] + "/" + array[0] );
    }
}
