package lexicon.se;

public abstract class Product {
    private String name;
    private double price;
    private int id;
    private int quantity;

    public Product(String name, double price, int id, int quantity) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setter for quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class Snack extends Product {
    private int calories;

    public Snack(String name, double price, int id, int quantity, int calories) {
        super(name, price, id, quantity);
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }
}

class Beverage extends Product {
    private int volume;

    public Beverage(String name, double price, int id, int quantity, int volume) {
        super(name, price, id, quantity);
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }
}

class Fruit extends Product {
    private String origin;

    public Fruit(String name, double price, int id, int quantity, String origin) {
        super(name, price, id, quantity);
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }
}
