//@author S. Murat. ÖZDEMİR
//@since 4.04.2023 - 21:22
package Assignment2;
import java.util.ArrayList;

public class Assignment02_20210808006 {
    public static void main(String[] args) {
        Store s = new Store("Migros", "www.migros.com.tr");

        Customer c = new Customer("CSE 102");

        ClubCustomer cc = new ClubCustomer("Club CSE 102", "05551234567");
//        s.addCustomer(c);
        s.addCustomer(cc);

        Product p = new Product(123456L, "Computer", 20, 1000.00);
        FoodProduct fp = new FoodProduct(456798L, "snickers", 100, 2, 250, true, true, true, false);
        CleaningProduct cp = new CleaningProduct(31654L, "Mop", 28, 99, false, "Multi-room");

        s.addProduct(p);
        s.addProduct(fp);
        s.addProduct(cp);

        System.out.println(s.getInventorySize());
//        System.out.println(s.getProduct("shoes"));

        System.out.println(cp.purchase(2));
        s.getProduct("Computer").addToInventory(3);
//        System.out.println(fp.purchase(200));

        c.addToCart(p, 2);
        c.addToCart(s.getProduct("snickers"), -2);
        c.addToCart(s.getProduct("snickers"), 1);
        System.out.println("Total due - " + c.getTotalDue());
        System.out.println("\n\nReceipt:\n" + c.receipt());

//        System.out.println("After paying: "+c.pay(2000));

        System.out.println("After paying: " + c.pay(2020));

        System.out.println("Total due - " + c.getTotalDue());
        System.out.println("\n\nReceipt 1:\n" + c.receipt());

//        Customer c2 = s.getCustomer("05551234568");
        cc.addToCart(s.getProduct("snickers"), 2);
        cc.addToCart(s.getProduct("snickers"), 1);
        System.out.println("\n\nReceipt 2:\n" + cc.receipt());

        Customer c3 = s.getCustomer("05551234567");
        c3.addToCart(s.getProduct("snickers"), 10);
        System.out.println("\n\nReceipt 3:\n" + cc.receipt());

        System.out.println(((ClubCustomer) c3).pay(26, false));

        c3.addToCart(s.getProduct(31654L), 3);
        System.out.println(c3.getTotalDue());
        System.out.println(c3.receipt());
        System.out.println(cc.pay(3 * 99, false));

        c3.addToCart(s.getProduct(31654L), 3);
        System.out.println(c3.getTotalDue());
        System.out.println(c3.receipt());
        System.out.println(cc.pay(3 * 99, true));
    }
}

class Product {
    private Long Id;
    private String Name;
    private double Price;
    private int Quantity;

    public Product(Long Id, String Name, int Quantity, double Price) {
        this.Id = Id;
        this.Name = Name;
        addToInventory(Quantity);
        setPrice(Price);
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
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
        if (price < 0) {
            throw new InvalidPriceException(price);
        }
        this.Price = price;
    }

    public int remaining() {
        return Quantity;
    }

    public int addToInventory(int amount) {
        if (amount < 0) {
            throw new InvalidAmountException(amount);
        }
        this.Quantity += amount;
        return this.Quantity;
    }

    public double purchase(int amount) {
        if (amount < 0) {
            throw new InvalidAmountException(amount);
        } else if (amount > Quantity) {
            throw new InvalidAmountException(amount, Quantity);
        } else {
            Quantity -= amount;
            return amount * this.Price;
        }
    }

    public String toString() {
        return "Product " + Name + " has " + Quantity + " remaining.";
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
    private int Calories;
    private boolean Dairy;
    private boolean Eggs;
    private boolean Peanuts;
    private boolean Gluten;

    public FoodProduct(Long Id, String Name, int Quantity, double Price, int calories, boolean Dairy, boolean eggs, boolean Peanuts, boolean gluten) {
        super(Id, Name, Quantity, Price);
        setCalories(calories);
        this.Dairy = Dairy;
        this.Eggs = eggs;
        this.Peanuts = Peanuts;
        this.Gluten = gluten;
    }

    public int getCalories() {
        return Calories;
    }

    public void setCalories(int calories) {
        if (calories < 0) {
            throw new InvalidAmountException(calories);
        }
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
}

class CleaningProduct extends Product {
    boolean Liquid;
    String WhereToUse;

    public CleaningProduct(Long Id, String Name, int Quantity, double Price, boolean liquid, String whereToUse) {
        super(Id, Name, Quantity, Price);
        this.Liquid = liquid;
        this.WhereToUse = whereToUse;
    }

    public boolean isLiquid() {
        return Liquid;
    }

    public String getWhereToUse() {
        return WhereToUse;
    }

    public void setWhereToUse(String whereToUse) {
        this.WhereToUse = whereToUse;
    }
}

class Customer {
    String Name;
    ArrayList<Product> Cart;
    ArrayList<Integer> Count;

    public Customer(String Name) {
        this.Name = Name;
        this.Cart = new ArrayList<>();
        this.Count = new ArrayList<>();
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

    public void addToCart(Product p, int count) {
        try {
            p.purchase(count);
            this.Cart.add(p);
            this.Count.add(count);
        } catch (InvalidAmountException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public String receipt() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Cart.size(); i++) {
            Product p = Cart.get(i);
            // Snickers - 2.0 X 2 = 4.0
            sb.append(p.getName())
                    .append(" - ")
                    .append(p.getPrice()).append(" X ")
                    .append(this.Count.get(i))
                    .append(" = ")
                    .append(p.getPrice() * this.Count.get(i))
                    .append("\n");
        }
        sb.append("\n----------------------------------------\n\nTotal Due = ")
                .append(getTotalDue());
        return sb.toString();
    }

    public double getTotalDue() {
        double total = 0;
        for (int i = 0; i < Cart.size(); i++) {
            Product p = Cart.get(i);
            total += p.getPrice() * this.Count.get(i);
        }
        return total;
    }

    public double pay(double amount) {
        double total = getTotalDue();
        if (amount < total) {
            throw new InsufficientFundsException(total, amount);
        }
        System.out.println("Thank you.");
        this.Cart.clear();
        this.Count.clear();

        return amount - total;
    }
}

class ClubCustomer extends Customer {
    private String Phone;
    private int Points;

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

    public double pay(double amount, boolean usePoints) {
        double total = getTotalDue();

        if (!usePoints) {
            if (amount < total) {
                throw new InsufficientFundsException(total, amount);
            }
            System.out.println("Thank you.");
            this.Cart.clear();
            this.Count.clear();
            Points += (int) (total);
            return amount - total;
        }

        double howMuchAreThePointsWorth = Points / 100.0; //100 points = 0.01 TL

        if (howMuchAreThePointsWorth > total) { // if the points are worth more than the total
            //since we have more points than the total, we have to subtract only the amount of points that we need to pay the total
            Points -= (int) (total * 100);
            System.out.println("Thank you.");
            this.Cart.clear();
            this.Count.clear();
            return amount;
            /* returns all the money to costumer that he/she paid
             because the points are worth more than the total and the costumer doesn't have to pay anything.*/

            // There is no point adding in this case because the total is 0.
        }

        total -= howMuchAreThePointsWorth;
        Points = 0; // we have to set the points to 0 because we have used all of them.
        if (amount < total) {
            throw new InsufficientFundsException(total, amount);
        }
        System.out.println("Thank you.");
        this.Cart.clear();
        this.Count.clear();
        Points += (int) (total);
        return amount - total;
    }
}

class Store {
    private String Name;
    private String Website;
    private ArrayList<ClubCustomer> clubCustomers;
    private ArrayList<Product> products;

    public Store(String Name, String Website) {
        this.Name = Name;
        this.Website = Website;
        this.products = new ArrayList<>();
        this.clubCustomers = new ArrayList<>();
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

    public void addProduct(Product p) {
        products.add(p);
    }

    public Product getProduct(Long id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        throw new ProductNotFoundException(id);
    }

    public Product getProduct(String name) {
        for (Product p : products) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        throw new ProductNotFoundException(name);
    }

    public void addCustomer(ClubCustomer customer) {
        clubCustomers.add(customer);
    }

    public ClubCustomer getCustomer(String phone) {
        for (ClubCustomer c : clubCustomers) {
            if (c.getPhone().equals(phone)) {
                return c;
            }
        }
        throw new CustomerNotFoundException(phone);
    }

    public void removeProduct(Long id) {
        for (Product p : products) {
            if (p.getId() == id) {
                products.remove(p);
                return;
            }
        }
        throw new ProductNotFoundException(id);
    }

    public void removeProduct(String name) {
        for (Product p : products) {
            if (p.getName().equals(name)) {
                products.remove(p);
                return;
            }
        }
        throw new ProductNotFoundException(name);
    }

    public void removeCustomer(String phone) {
        for (ClubCustomer c : clubCustomers) {
            if (c.getPhone().equals(phone)) {
                clubCustomers.remove(c);
                return;
            }
        }
        throw new CustomerNotFoundException(phone);
    }
}

class CustomerNotFoundException extends IllegalArgumentException {
    private String phone;

    public CustomerNotFoundException(String phone) {
        this.phone = phone;
    }

    @Override
    public String getMessage() {
        return "CustomerNotFoundException: " + phone;
    }

    @Override
    public String toString() {
        return "CustomerNotFoundException: " + phone;
    }
}

class InsufficientFundsException extends RuntimeException {
    double total;
    double payment;

    public InsufficientFundsException(double total, double payment) {
        this.total = total;
        this.payment = payment;
    }

    @Override
    public String getMessage() {
        return "InsufficientFundsException: " + total + " due, but only  " + payment + " given";
    }

    @Override
    public String toString() {
        return "InsufficientFundsException: " + total + " due, but only  " + payment + " given";
    }
}

class InvalidAmountException extends RuntimeException {
    private double amount;
    private int quantity = 0;

    public InvalidAmountException(double amount) {
        this.amount = amount;
    }

    public InvalidAmountException(double amount, int quantity) {
        this.amount = amount;
        this.quantity = quantity;
    }

    @Override
    public String getMessage() {
        return quantity == 0 ? "InvalidAmountException: " + amount : "InvalidAmountException: " + amount + " was requested but only " + quantity + " remaining";
    }

    @Override
    public String toString() {
        return quantity == 0 ? "InvalidAmountException: " + amount : "InvalidAmountException: " + amount + " was requested but only " + quantity + " remaining";
    }
}

class InvalidPriceException extends RuntimeException {
    private double price;

    public InvalidPriceException(double price) {
        this.price = price;
    }

    @Override
    public String getMessage() {
        return "InvalidPriceException: " + price;
    }

    @Override
    public String toString() {
        return "InvalidPriceException: " + price;
    }
}

class ProductNotFoundException extends IllegalArgumentException {
    private String name;
    private Long id;

    public ProductNotFoundException(String name) {
        this.name = name;
        id = null;
    }

    public ProductNotFoundException(Long id) {
        this.id = id;
        name = null;

    }

    @Override
    public String getMessage() {
        return name == null ? "ProductNotFoundException: " + id : "ProductNotFoundException: " + name;
    }

    @Override
    public String toString() {
        return name == null ? "ProductNotFoundException: " + id : "ProductNotFoundException: " + name;
    }
}