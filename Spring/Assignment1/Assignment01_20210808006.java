//@author: Said Murat ÖZDEMİR
//@since: 16.03.2023

import java.util.ArrayList;

public class Assignment01_20210808006 {
    public static void main(String[] args) {
        Store s = new Store("Migros", "www.migros.com.tr");
        Customer c = new Customer("CSE 102");
        System.out.println(c);

        ClubCustomer cc = new ClubCustomer("Club CSE 102", "05551234567");
        cc.addPoints(20);
        cc.addPoints(30);
        System.out.println(cc.getPhone());
        System.out.println(cc);

        Product p = new Product("1234", "Computer", 20, 1000.00);
        FoodProduct fp = new FoodProduct("3456", "Snickers", 100, 2, 250, true, true, true, false);
        CleaningProduct cp = new CleaningProduct("5678", "Mop", 28, 99, false, "Multi-room");
        s.addProduct(p);
        s.addProduct(fp);
        for (int i = 0; i < s.getInventorySize(); i++) {
            System.out.println(s.getProduct(i));
        }

        s.addProduct(cp);
        s.addProduct(new Product("4321", "iPhone", 50, 99.00));
        System.out.println(s.getProductIndex(new FoodProduct("8888", "Apples", 500, 1, 50, false, false, false, false)));
        System.out.println(cp.purchase(2));
        if (fp.containsGluten()) {
            System.out.println("My wife cannot eat or drink " + fp.getName());
        } else {
            System.out.println("My wife can eat or drink " + fp.getName());
        }
        if (fp.containsPeanuts()) {
            System.out.println("My friend cannot eat or drink " + fp.getName());
        } else {
            System.out.println("My friend can eat or drink " + fp.getName());
        }
        s.getProduct(0).addToInventory(3);

        for (int i = 0; i < s.getInventorySize(); i++) {
            Product cur = s.getProduct(i);
            System.out.println(cur);
            for (int j = i + 1; j < s.getInventorySize(); j++) {
                if (cur.equals(s.getProduct(j))) {
                    System.out.println(cur.getName() + " is the same price as " + s.getProduct(j).getName());
                }
            }
        }

    }

}

class Product {

    String Id;
    String Name;
    double Price;
    int Quantity;

    public Product() {
    }

    public Product(String Id, String Name, int Quantity, double Price) {
        this.Id = Id;
        this.Name = Name;
        this.Quantity = Quantity;
        this.Price = Price;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        this.Price = price;
    }

    public int remaining() {
        return Quantity;
    }

    public int addToInventory(int amount) {
        if (amount > 0) {
            this.Quantity += amount;
        }
        return this.Quantity;
    }

    public double purchase(int amount) {
        if (amount > 0 && amount <= this.Quantity) {
            this.Quantity -= amount;
            return amount * this.Price;
        }
        return 0;
    }

    public String toString() {
        return "Product " + this.Name + " has " + this.Quantity + " remaining.";
    }

    public boolean equals(Object o) {
        if (o instanceof Product) {
            Product p = (Product) o;
            return Math.abs(this.Price - p.Price) < 0.001;
        }
        return false;
    }
}

class FoodProduct extends Product {
    int Calories;
    boolean Dairy;
    boolean Eggs;
    boolean Peanuts;
    boolean Gluten;

    public int getCalories() {
        return Calories;
    }

    public void setCalories(int calories) {
        this.Calories = calories;
    }

    public boolean containsDairy() {
        return Dairy;
    }

    public boolean containsEggs() {
        return Eggs;
    }

    public boolean containsPeanuts() {
        return Peanuts;
    }

    public boolean containsGluten() {
        return Gluten;
    }

    public FoodProduct() {
    }

    public FoodProduct(String Id, String Name, int Quantity, double Price, int calories, boolean Dairy, boolean eggs, boolean Peanuts, boolean gluten) {
        super(Id, Name, Quantity, Price);
        this.Calories = calories;
        this.Dairy = Dairy;
        this.Eggs = eggs;
        this.Peanuts = Peanuts;
        this.Gluten = gluten;
    }

}

class CleaningProduct extends Product {
    boolean Liquid;
    String WhereToUse;

    public boolean isLiquid() {
        return Liquid;
    }

    public String getWhereToUse() {
        return WhereToUse;
    }

    public void setWhereToUse(String whereToUse) {
        this.WhereToUse = whereToUse;
    }

    public CleaningProduct() {
    }

    public CleaningProduct(String Id, String Name, int Quantity, double Price, boolean liquid, String whereToUse) {
        super(Id, Name, Quantity, Price);
        this.Liquid = liquid;
        this.WhereToUse = whereToUse;
    }

}

class Customer {
    String Name;

    public Customer() {
    }

    public Customer(String Name) {
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String toString() {
        return this.Name;
    }
}

class ClubCustomer extends Customer {
    String Phone;
    int Points;

    public ClubCustomer() {
    }

    public ClubCustomer(String Name, String Phone) {
        super(Name);
        this.Phone = Phone;
        this.Points = 0;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getPoints() {
        return Points;
    }

    public void addPoints(int points) {
        if (points > 0) {
            this.Points += points;
        }
    }

    public String toString() {
        return super.Name + " has " + this.Points + " points";
    }

}

class Store {
    String Name;
    String Website;
    ArrayList<Product> products;

    public Store() {
    }

    public Store(String Name, String Website) {
        this.Name = Name;
        this.Website = Website;
        this.products = new ArrayList<Product>();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public int getInventorySize() {
        return products.size();
    }

    public void addProduct(Product p, int index) {
        if (index < 0 || index > getInventorySize()) {
            products.add(p);
        } else {
            products.add(index, p);
        }
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public Product getProduct(int index) {
        if (index < 0 || index >= getInventorySize()) {
            return null;
        }
        return products.get(index);
    }

    public int getProductIndex(Product p) {
        if (p == null) {
            return -1;
        }
        return products.indexOf(p);
    }

}