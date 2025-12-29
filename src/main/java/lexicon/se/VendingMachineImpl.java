package lexicon.se;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class VendingMachineImpl implements IVendingMachine {
    private final List<Integer> acceptedCoins = Arrays.asList(1, 5, 10, 20, 50);
    private List<Product> products;
    private int balance;

    public VendingMachineImpl() {
        this.products = new ArrayList<>();
        initializeProducts();
    }

    // 0=name, 1=price, 2=id, 3=quantity, 4=calories/volume/origin (depending on product type)
    private void initializeProducts() {
        products.add(new Snack("Chips", 15, 1, 10, 250));
        products.add(new Snack("Chocolate Bar", 20, 2, 3, 300));
        products.add(new Beverage("Soda", 25, 3, 8, 500));
        products.add(new Beverage("Water", 10, 4, 15, 600));
        products.add(new Fruit("Apple", 12, 5, 20, "USA"));
    }

    @Override
    public void insertCoin(int coin) {
        if (!acceptedCoins.contains(coin)) {
            throw new IllegalArgumentException("Coin of value " + coin + " is not accepted.");
        }
        balance += coin;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public Product purchaseProduct(int productId) {
        Product product = null;
        for (Product p : getProducts()) {
            if (p.getId() == productId) {
                product = p;
                break;
            }
        }
        if (product == null) {
            throw new IllegalArgumentException("Product not found.");
        }
        if (product.getQuantity() <= 0) {
            throw new IllegalArgumentException("Product out of stock.");
        }

        int price = (int) Math.round(product.getPrice());
        if (this.balance < price) {
            throw new IllegalArgumentException("Insufficient balance.");
        }

        // Update quantity and balance
        product.setQuantity(product.getQuantity() - 1);
        this.balance -= price;

        return product;

    }

    @Override
    public int returnChange() {
        int change = balance;
        balance = 0;
        return change;
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

}
