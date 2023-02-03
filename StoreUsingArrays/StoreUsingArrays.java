//@author Said Murat ÖZDEMİR
//Date: 25.11.2022
import java.util.Scanner;
public class StoreUsingArrays {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] item = {"bread", "cola", "snickers", "AYRAN"};
        int[] quantity = {10, 15, 12, 30};
        float[] price = {0.75f, 2.5f, 2.25f, 1f};
        storeRun(item, quantity, price, input);
    }
    public static void capitalFirstLetter(String[] item) {
        for (int i = 0; i < item.length; i++) {
            item[i] = item[i].substring(0, 1).toUpperCase() + item[i].substring(1).toLowerCase();
        }
    }
    public static int menu(String[] item, float[] price, Scanner input) {
        System.out.println("Please enter what you would like: ");
        for (int i = 0; i < item.length; i++) {
            System.out.println((i + 1) + " - for " + item[i] + " (" + price[i] + ")" );
        }
        System.out.println("0 - to checkout");
        return input.nextInt();
    }
    public static String returnedAmounts(float amount) {
        String result = "";
        int[] bills = {200, 100, 50, 20, 10, 5, 1};
        float[] coins = {0.50f, 0.25f, 0.10f, 0.05f, 0.01f};
        for (int bill : bills) {
            int count = (int) (amount / bill);
            if (count > 0) {
                result += count + " - " + bill + "\n";
            }
            amount = amount % bill;
        }
        for (float coin : coins) {
            int count = (int) (amount / coin);
            if (count > 0) {
                result += count + " - " + coin + "\n";
            }
            amount = amount % coin;
        }
        return result;
    }

    public static void storeRun(String[] item, int[] quantity, float[] price, Scanner input) {
        System.out.println("Welcome to our store, we have the following. ");
        capitalFirstLetter(item);
        int choice = menu(item, price, input);
        int[] quantityBought = new int[item.length];
        while (choice != 0) {
            if (choice > 0 && choice <= item.length) {
                System.out.print("How many " + item[choice - 1] + " would you like to buy? ");
                int amountUserWants = input.nextInt();
                if (amountUserWants > 0 && amountUserWants <= quantity[choice - 1]) {
                    quantity[choice - 1] -= amountUserWants;
                    quantityBought[choice - 1] += amountUserWants;
                } else {
                    System.out.println("ERROR: Invalid request");
                }
            } else {
                System.out.println("ERROR: Invalid choice.");
            }
            choice = menu(item, price, input);
        }
        float total = 0f;
        for (int i = 0; i < item.length; i++) {
            total += (price[i] * quantityBought[i]);
        }
        System.out.println("****** Customer Total ******");

        for (int i = 0; i < item.length; i++) {
            if (quantityBought[i] > 0) {
                System.out.println(item[i] + " - " + quantityBought[i] + " * " + price[i] + " = " + (price[i] * quantityBought[i]));
            }
        }
        System.out.println("-------------------------------");
        System.out.println("Total due - " + total + "\n");
        System.out.println("Please enter the amount given");
        float amountGiven = input.nextFloat();

        while (amountGiven < total) {
            System.out.println("Not enough payment given");
            System.out.println("\nPlease enter the amount given: ");
            amountGiven = input.nextFloat();
        }
        System.out.println("Thank you for your business. Your change given is: ");
        System.out.println(returnedAmounts(amountGiven - total));
    }
}
