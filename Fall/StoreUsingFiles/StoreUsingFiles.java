//@author Said Murat Özdemir
//@since 11.12.2022
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class StoreUsingFiles {

    public static void main(String[] args){
        try {
            String filenameProduct = args[0] + "_ProductionInfo.txt";
            int productNumber = countProducts(filenameProduct);

            String[] itemID = new String[productNumber];
            String[] itemName = new String[productNumber];
            int[] quantity = new int[productNumber];
            double[] price = new double[productNumber];

            getProductInfo(itemID, itemName, quantity, price, filenameProduct);

            int customerRequestAmount = countProducts(args[0] + "_Order.txt");
            int[] customerBought = new int[productNumber];

            File file = new File(args[0] + "_Order.txt");
            Scanner scan = new Scanner(file);

            String[] customerID = new String[customerRequestAmount];
            int[] customerQuantity = new int[customerRequestAmount];

            for (int i = 0; i < customerRequestAmount; i++) {
                customerID[i] = scan.next();
                customerQuantity[i] = scan.nextInt();
            }
            scan.close();

            String errors = "";

            for (int i = 0; i < customerID.length; i++) {
                int count = 0;
                for (int j = 0; j < itemID.length; j++) {
                    if (customerID[i].equals(itemID[j])) {
                        if (customerQuantity[i] < 0) {
                            errors += "ERROR: Invalid amount requested (" + customerQuantity[i] + ")\n";
                            count++;
                        }
                        else if (customerQuantity[i] > quantity[j]) {
                            errors += "ERROR: " + itemName[j] + " - " + customerQuantity[i] + " requested but only " + quantity[j] + " remaining\n";
                            count++;
                        }
                        else {
                            customerBought[j] += customerQuantity[i];
                            quantity[j] -= customerQuantity[i];
                            count++;
                        }
                    }else if (j == itemID.length - 1 && count == 0) {
                        errors += "ERROR: Product " + customerID[i] + " not found\n";
                    }

                }

            }

            File receipt = new File(args[0] + "_Receipt.txt");
            PrintWriter receiptWriter = new PrintWriter(receipt);
            receiptWriter.println("********* Customer Receipt *********");
            double total = 0;
            for (int i = 0; i < itemID.length; i++) {
                if(customerBought[i] != 0){
                    receiptWriter.println(itemName[i] + "(" + itemID[i] + ") - " + customerBought[i] +
                            " * " + price[i] + " = " + (customerBought[i]*price[i]) );
                    total += customerBought[i]*price[i];
                }
            }
            receiptWriter.println("------------------------------------");
            receiptWriter.println("Total due - " + total);
            receiptWriter.close();


            File errorFile = new File(args[0] + ".log");
            PrintWriter errorWriter = new PrintWriter(errorFile);
            errorWriter.print(errors);
            errorWriter.close();
            writeProductInfo(itemID, itemName, quantity, price, args[0] + "_ProductInfoAfterOrder.txt");




        } catch (IOException e) {
            //ERROR MESSAGE
            System.out.println("File Exception");
        }


    }
    public static int countProducts(String fileName) throws FileNotFoundException {
        int count = 0;
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            count++;
            scan.nextLine();
        }
        scan.close();

        return count;
    }
    public static void getProductInfo(String[] itemID,String[] itemName,int[] quantity, double[] price, String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scan = new Scanner(file);
        for (int i = 0; i < itemID.length; i++) {
            itemID[i] = scan.next();
            itemName[i] = scan.next();
            quantity[i] = scan.nextInt();
            price[i] = scan.nextDouble();
        }
        scan.close();
    }
    public static void writeProductInfo(String[] itemID,String[] itemName,int[] quantity, double[] price, String filename) throws FileNotFoundException {
        File file = new File(filename);
        PrintWriter writer = new PrintWriter(file);
        for (int i = 0; i < itemID.length; i++) {
            if(quantity[i] != 0){
                writer.println(itemID[i] + " " + itemName[i] + " " + quantity[i] + " " + price[i]);
            }
        }
        writer.close();
    }
}
