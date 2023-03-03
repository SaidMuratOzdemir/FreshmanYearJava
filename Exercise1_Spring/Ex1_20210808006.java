import java.util.Scanner;

public class Ex1_20210808006 {
    public static void main(String[] args) {

        Stock stock = new Stock("ORCL", "Oracle Corporation");
        stock.previousClosingPrice = 34.5;
        stock.currentPrice = 34.35;
        System.out.println("The price-change percentage is " + stock.getChangePercent());


        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of fans: ");
        int fanNumber = input.nextInt();

        Fan[] fans = new Fan[fanNumber];

        for (int i = 0, j = 0; i < fans.length; i++) {
            if (i % 2 == 0) {
                fans[i] = new Fan();
            } else {
                fans[i] = new Fan(j++, "yellow");
            }
        }
    }

    public static void increaseSpeedOfThirdFans(Fan[] fans) {
        for (int i = 2; i < fans.length; i += 3) {
            if (fans[i].isOn()) {
                int currentSpeed = fans[i].getSpeed();
                if (currentSpeed == fans[i].FAST) {
                    fans[i].setSpeed(fans[i].SLOW);
                } else {
                    fans[i].setSpeed(currentSpeed + 1);
                }
            }
        }
    }
}

class Stock {
    String symbol;
    String name;
    double previousClosingPrice;
    double currentPrice;

    Stock(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public double getChangePercent() {
        return ((currentPrice - previousClosingPrice) / previousClosingPrice) * 100;
    }
}

class Fan {
    final int SLOW = 1;
    final int MEDIUM = 2;
    final int FAST = 3;
    private int speed = SLOW;
    private boolean on = false;
    private double radius = 5;
    private String color = "blue";

    Fan() {

    }

    Fan(double radius, String color) {
        this.radius = radius;
        this.color = color;
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isOn() {
        return this.on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toString() {
        if (on) {
            return "Fan speed: " + speed + ", color: " + color + ", radius: " + radius;
        } else {
            return "Fan color: " + color + ", radius: " + radius + ", fan is off";
        }
    }
}
