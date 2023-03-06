package cinema;

public class CinemaSold {
    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        java.util.Scanner input = new java.util.Scanner(System.in);
        int rows = input.nextInt();
        System.out.println("Enter the number of seats in each row:\n");
        int eachSeat = input.nextInt();

        if (rows * eachSeat < 60) {
            System.out.println("Total income:\n $" + (rows * eachSeat * 10));
            System.exit(31);
        } else {
            System.out.println("Total income:\n $" + (rows / 2 * 10 * eachSeat + (rows - rows / 2) * eachSeat * 8));
        }
    }
}
