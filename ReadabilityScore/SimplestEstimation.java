package ReadabilityScore;

import java.util.Scanner;

public class SimplestEstimation {
    public static void main (String[] args){
        Scanner input = new Scanner(System.in);
        String text = input.nextLine();
        System.out.println(text.length()>100 ? "HARD" : "EASY");
    }
}
