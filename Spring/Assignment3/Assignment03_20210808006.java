//package Assignment3;

//@author S. Murat. ÖZDEMİR
//@since 27.05.2023 - 22:01

import java.util.ArrayList;

public class Assignment03_20210808006 {
    public static void main(String[] args) {
        Store s1 = new Store("Migros", "www.migros.com.tr");
        Store s2 = new Store("Bim", "www.bim.com.tr");
        Customer c = new Customer("CSE 102");
        Customer cc = new Customer("Club CSE 102");
        s1.addCustomer(cc);
        s2.addCustomer(cc);
        Product p = new Product(123456L, "Computer", 1000.00);
        FoodProduct fp = new FoodProduct(456789L, "Snickers", 2, 250, true, true, true, false);
        CleaningProduct cp = new CleaningProduct(31654L, "Mop", 99, false, "Multi-room");
        System.out.println(cp);
        s1.addToInventory(p, 20);
        s2.addToInventory(p, 10);
        s2.addToInventory(fp, 100);
        s1.addToInventory(cp, 28);
        System.out.println(s1.getName() + " has " + s1.getCount() + " products");
        System.out.println(s1.getProductCount(p));
        System.out.println(s1.purchase(p, 2));
        s1.addToInventory(p, 3);
        System.out.println(s1.getProductCount(p));
        System.out.println(s2.getProductCount(p));
        c.addToCart(s1, p, 2);
        c.addToCart(s1, fp, 1);
        c.addToCart(s1, cp, 1);
        System.out.println("Total due - " + c.getTotalDue(s1));
        System.out.println("\n \nReceipt: " + c.receipt(s1));
        System.out.println("After paying:  " + c.pay(s1, 2100, true));
        cc.addToCart(s2, fp, 2);
        cc.addToCart(s2, fp, 1);
        System.out.println(cc.receipt(s2));
        cc.addToCart(s2, fp, 10);
        System.out.println(cc.receipt(s2));

    }
}

class Product {
    private long Id;
    private String Name;
    private double Price;

    public Product(long Id, String Name, double Price) {
        this.Id = Id;
        this.Name = Name;
        setPrice(Price);
    }


    public long getId() {
        return Id;
    }

    public void setId(long id) {
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

    public String toString() {
        return Id + " - " + Name + " @ " + Price;
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

    public FoodProduct(long Id, String Name, double Price, int calories, boolean Dairy, boolean eggs, boolean Peanuts, boolean gluten) {
        super(Id, Name, Price);
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

    public CleaningProduct(long Id, String Name, double Price, boolean liquid, String whereToUse) {
        super(Id, Name, Price);
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
    private String name;
    private ArrayList<Product> cart;
    private ArrayList<Integer> count;

    public Customer(String name) {
        this.name = name;
        this.cart = new ArrayList<>();
        this.count = new ArrayList<>();
    }

    public void addToCart(Store store, Product product, int count) {
        if (!store.products.contains(product)) {
            System.out.println("ERROR: Product not found");
        }

        if (store.productCounts.get(store.products.indexOf(product)) < count) {
            System.out.println("ERROR: Not enough stock");
        }

        if (cart.contains(product)) {
            int index = cart.indexOf(product);
            cart.set(index, product);
            this.count.set(index, count);
            store.productCounts.set(store.products.indexOf(product), store.productCounts.get(store.products.indexOf(product)) - count);
        } else {
            cart.add(product);
            this.count.add(count);
            store.productCounts.set(store.products.indexOf(product), store.productCounts.get(store.products.indexOf(product)) - count);
        }

    }

    public String receipt(Store store) {

        if (cart.isEmpty()) {
            throw new StoreNotFoundException(store.getName());
        }

        StringBuilder sb = new StringBuilder();
        sb.append(store.getName()).append(" Receipt:\n");

        for (Product product : cart) {
            sb.append(product.getId())
                    .append(" - ")
                    .append(product.getName())
                    .append(" @ ")
                    .append(product.getPrice())
                    .append(" X ")
                    .append(store.getProductCount(product))
                    .append("\n");
        }

        //total amount for all Products
        sb.append("Total Amount: ")
                .append(getTotalDue(store))
                .append("\n");

        return sb.toString();
    }

    public double getTotalDue(Store store) {

        if (cart.isEmpty()) {
            throw new StoreNotFoundException(store.getName());
        }

        double totalAmount = 0;
        for (Product product : cart) {
            totalAmount += product.getPrice() * store.getProductCount(product);
        }
        return totalAmount;
    }

    public int getPoints(Store store) {
        int points = 0;
        for (int point : store.points) {
            points += point;
        }
        return points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //TODO: pay method

    public double pay(Store store, double amount, boolean usePoints) throws InsufficientFundsException, StoreNotFoundException {
        if (cart == null) {
            throw new StoreNotFoundException(store.getName());
        }

        double totalDue = getTotalDue(store);

        if (amount >= totalDue) {
            double change = amount - totalDue;
            System.out.println("Thank you!");

            if (usePoints) {
                //fix this
            }
            return change;
        } else {
            throw new InsufficientFundsException(getTotalDue(store), amount);
        }
    }
}

class Store {
    private String Name;
    private String Website;
    ArrayList<Product> products;
    ArrayList<Integer> productCounts;
    ArrayList<Customer> customers;
    ArrayList<Integer> points;

    public Store(String Name, String Website) {
        this.Name = Name;
        this.Website = Website;
        this.products = new ArrayList<>();
    }

    public int getCount() {
        return products.size();
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

    public int remaining(Product product) {
        int count = 0;
        for (Product p : products) {
            if (p.equals(product)) {
                count += productCounts.get(products.indexOf(p));
            }
        }
        if (count == 0) {
            throw new ProductNotFoundException(product);
        }
        return count;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        points.add(0);
    }

    public int getProductCount(Product product) {
        int count = 0;
        for (Product p : products) {
            if (p.equals(product)) {
                count += productCounts.get(products.indexOf(p));
            }
        }
        if (count == 0) {
            throw new ProductNotFoundException(product);
        }
        return count;
    }

    public int getCustomerPoints(Customer customer) {
        int index = customers.indexOf(customer);
        if (index == -1) {
            throw new CustomerNotFoundException(customer);
        }
        return points.get(index);
    }

    public void removeProduct(Product product) {
        if (!products.contains(product)) {
            throw new ProductNotFoundException(product);
        }
        products.remove(product);
        points.remove(products.indexOf(product));
    }

    public void addToInventory(Product product, int amount) {
        if (amount < 0) {
            throw new InvalidAmountException(amount);
        }

        if (products.contains(product)) {
            int index = products.indexOf(product);
            productCounts.set(index, productCounts.get(index) + amount);
        } else {
            products.add(product);
            productCounts.add(amount);
        }
    }

    public double purchase(Product product, int amount) {
        if (!products.contains(product)) {
            throw new ProductNotFoundException(product);
        }

        int index = products.indexOf(product);
        if (productCounts.get(index) < amount || amount < 0) {
            throw new InvalidAmountException(amount, productCounts.get(index));
        }

        productCounts.set(index, productCounts.get(index) - amount);
        return product.getPrice() * amount;
    }
}

class StoreNotFoundException extends IllegalArgumentException {
    private String name;

    public StoreNotFoundException(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StoreNotFoundException: " + name;
    }
}

class CustomerNotFoundException extends IllegalArgumentException {
    private Customer customer;

    public CustomerNotFoundException(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "CustomerNotFoundException: " + customer.getName();
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
    private int amount;
    private int quantity = 0;

    public InvalidAmountException(int amount) {
        this.amount = amount;
    }

    public InvalidAmountException(int amount, int quantity) {
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
    private Product product;

    public ProductNotFoundException(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductNotFoundException: " + product.getName();
    }
}