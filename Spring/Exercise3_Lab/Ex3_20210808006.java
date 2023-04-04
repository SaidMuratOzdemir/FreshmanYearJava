public class Ex3_20210808006 {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}

class Author {
    private String name;
    private String surname;
    private String mail;

    public Author(String name, String surname, String mail) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMail() {
        return mail;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Book {
    private String isbn;
    private String title;
    private Author author;
    private double price;

    public Book(String isbn, String title, Author author, double price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public Book(String isbn, String title, Author author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = 15.25;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

class EBook extends Book {
    private String downloadUrl;
    private int sizeMB;

    public EBook(String isbn, String title, Author author, double price, String downloadUrl, int sizeMB) {
        super(isbn, title, author, price);
        this.downloadUrl = downloadUrl;
        this.sizeMB = sizeMB;
    }

    public EBook(String isbn, String title, Author author, String downloadUrl, int sizeMB) {
        super(isbn, title, author);
        this.downloadUrl = downloadUrl;
        this.sizeMB = sizeMB;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public int getSizeMB() {
        return sizeMB;
    }
}

class PaperBook extends Book {
    private int shippingWeight;
    private boolean inStock;

    public PaperBook(String isbn, String title, Author author, double price, int shippingWeight, boolean inStock) {
        super(isbn, title, author, price);
        this.shippingWeight = shippingWeight;
        this.inStock = inStock;
    }

    public PaperBook(String isbn, String title, Author author, double price, boolean inStock) {
        super(isbn, title, author, price);
        this.shippingWeight = (int)(Math.random() * 10 + 5);
        this.inStock = inStock;
    }

    public int getShippingWeight() {
        return shippingWeight;
    }

    public boolean getInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}