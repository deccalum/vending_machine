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
            
            System.out.println("\nEnter coin (1/5/10/20/50), product ID to buy, or 0 to exit and get change:");
            String input = scanner.nextLine();
            
            if (input.equals("0")) {
                int change = vendingMachine.returnChange();
                System.out.println("Returned change: " + change);
                System.out.println("Goodbye!");
                break;
            }
            
            if (input.equals("1") || input.equals("5") || input.equals("10") || input.equals("20") || input.equals("50")) {
                vendingMachine.insertCoin(Integer.parseInt(input));
                System.out.println("Inserted coin: " + input);
            } else {
                Product purchased = vendingMachine.purchaseProduct(input);
                System.out.println("Purchased: " + purchased.getName());
            }
        }
    }
}