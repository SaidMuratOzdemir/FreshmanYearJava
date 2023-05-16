//package Exercise6_Lab;

import java.util.ArrayList;
import java.util.Date;

//@author S. Murat. ÖZDEMİR
//@since 30.04.2023 - 01:05
public class Ex6_20210808006 {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

abstract class Product implements Comparable<Product> {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int compareTo(Product o) {
        if (this.price > o.price) {
            return 1;
        } else if (this.price < o.price) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[name=" + name + ", price=" + price + "]";
    }
}

abstract class Book extends Product {
    private String author;
    private int pageCount;

    public Book(String name, double price, String author, int pageCount) {
        super(name, price);
        this.author = author;
        this.pageCount = pageCount;
    }

    public String getAuthor() {
        return author;
    }

    public int getPageCount() {
        return pageCount;
    }
}

class ReadingBook extends Book {
    private String genre;

    public ReadingBook(String name, double price, String author, int pageCount, String genre) {
        super(name, price, author, pageCount);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}

class ColoringBook extends Book implements Colorable {
    private String color;

    public ColoringBook(String name, double price, String author, int pageCount) {
        super(name, price, author, pageCount);
    }

    public String getColor() {
        return color;
    }

    public void paint(String color) {
        this.color = color;
    }
}

class ToyHorse extends Product implements Rideable {
    public ToyHorse(String name, double price) {
        super(name, price);
    }

    public void ride() {
    }
}

class Bicycle extends Product implements Colorable, Rideable {
    private String color;

    public Bicycle(String name, double price) {
        super(name, price);
    }

    public String getColor() {
        return color;
    }

    public void paint(String color) {
        this.color = color;
    }

    public void ride() {
    }
}

class User {
    private String username;
    private String email;
    private PaymentMethod payment;
    private ArrayList<Product> cart;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.cart = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setPayment(PaymentMethod payment) {
        this.payment = payment;
    }

    public Product getProduct(int index) {
        return cart.get(index);
    }

    public void removeProduct(int index) {
        cart.remove(index);
    }

    public void addProduct(Product product) {
        cart.add(product);
    }

    public void purchase() {
        double total = 0;
        for (Product product : cart) {
            total += product.getPrice();
        }
        if (payment.pay(total)) {
            cart.clear();
        }
    }
}

class CreditCard implements PaymentMethod {
    private long cardNumber;
    private String cardHolderName;
    private Date expirationDate;
    private int cvv;

    public CreditCard(long cardNumber, String cardHolderName, Date expirationDate, int cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    public boolean pay(double amount) {
        return true;
    }
}

class Paypal implements PaymentMethod {
    private String username;
    private String password;

    public Paypal(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean pay(double amount) {
        return true;
    }
}

interface Colorable {
    void paint(String color);
}

interface Rideable {
    void ride();
}

interface PaymentMethod {
    boolean pay(double amount);
}