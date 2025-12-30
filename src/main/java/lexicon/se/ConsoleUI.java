package lexicon.se;

import java.util.Scanner;

public class ConsoleUI {

    Scanner scanner = new Scanner(System.in);
    IVendingMachine vendingMachine = new VendingMachineImpl();

    public void start() {
        while (true) {
            System.out.println("\n=== Vending Machine ===");
            System.out.println("Balance: " + vendingMachine.getBalance());
            System.out.println("\nProducts:");
            for (Product p : vendingMachine.getProducts()) {
                System.out.println(p.getId() + ": " + p.getName() + " - " + p.getPrice() + " (" + p.getQuantity() + " left)");
            }

            System.out.println("\nEnter coin " + vendingMachine.getAcceptedCoins() + ", product ID to buy, or [0] to exit and get change:");
            String input = scanner.nextLine().trim();
            
            if (input.equals("0")) {
                int change = vendingMachine.returnChange();
                System.out.println("Returned change: " + change);
                System.out.println("Goodbye!");
                break;
            }
            
            Integer coinValue = null;
            try {
                coinValue = Integer.valueOf(input);
            } catch (NumberFormatException ignore) {
                // non-numeric input -> treat as product id
            }

            if (coinValue != null) {
                if (vendingMachine.getAcceptedCoins().contains(coinValue)) {
                    vendingMachine.insertCoin(coinValue);
                    System.out.println("Inserted coin: " + coinValue);
                } else {
                    System.out.println("Coin " + coinValue + " is not accepted. Valid coins: " + vendingMachine.getAcceptedCoins());
                }
                continue;
            }

            Product target = null;
            for (Product p : vendingMachine.getProducts()) {
                if (p.getId().equals(input)) {
                    target = p;
                    break;
                }
            }

            if (target == null) {
                System.out.println("Unknown product id. Please use one of the listed ids.");
                continue;
            }

            try {
                Product purchased = vendingMachine.purchaseProduct(input);
                System.out.println("Purchased: " + purchased.getName());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}