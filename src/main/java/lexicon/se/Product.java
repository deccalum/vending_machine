package lexicon.se;

public abstract class Product {
    private String id;
    private String name;
    private int price;
    private int quantity;

    public Product(String name, int price, String id, int quantity) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setter for quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    String getDescription() {
        return name + " (ID: " + id + ") - Price: " + price + ", Quantity: " + quantity;
    }
}

class Snack extends Product {
    private int calories;
    
    // tracks how many products of this type have been created
    private static int idCounter = 1;

    public Snack(String name, int price, int quantity, int calories) {
        super(name, price, "A" + idCounter++, quantity);
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }
}

class Beverage extends Product {
    private int volume;

    // tracks how many products of this type have been created
    private static int idCounter = 1;

    public Beverage(String name, int price, int quantity, int volume) {
        super(name, price, "B" + idCounter++, quantity);
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }
}

class Fruit extends Product {
    private String origin;

    // tracks how many products of this type have been created
    private static int idCounter = 1;

    public Fruit(String name, int price, int quantity, String origin) {
        super(name, price, "C" + idCounter++, quantity);
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }
}
