package lexicon.se;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VendingMachineImplTest {
    private VendingMachineImpl vendingMachine;

    @BeforeEach
    void setUp() {
        vendingMachine = new VendingMachineImpl();
    }

    // Test Case 1 — Insert Valid Coin
    @Test
    void testInsertValidCoin() {
        vendingMachine.insertCoin(10);
        assertEquals(10, vendingMachine.getBalance());
    }

    // Test Case 2 — Reject Invalid Coin
    @Test
    void testRejectInvalidCoin() {
        assertThrows(IllegalArgumentException.class, () -> vendingMachine.insertCoin(7));
        assertEquals(0, vendingMachine.getBalance());
    }

    // Test Case 3 — Purchase Product Successfully
    @Test
    void testPurchaseProductSuccessfully() {
        // Find a product to test with (e.g., "Chocolate Bar", price 20)
        Product product = findProductByName("Chocolate Bar");
        assertNotNull(product, "Product 'Chocolate Bar' should exist");
        
        String id = product.getId();
        int initialQty = product.getQuantity();
        int price = product.getPrice();

        vendingMachine.insertCoin(price);
        Product purchased = vendingMachine.purchaseProduct(id);

        assertNotNull(purchased);
        assertEquals("Chocolate Bar", purchased.getName());
        assertEquals(0, vendingMachine.getBalance());
        assertEquals(initialQty - 1, purchased.getQuantity());
    }

    // Test Case 4 — Purchase Fails (Insufficient Balance)
    @Test
    void testPurchaseFailsInsufficientBalance() {
        Product product = findProductByName("Chocolate Bar");
        assertNotNull(product);
        String id = product.getId();
        int initialQty = product.getQuantity();

        vendingMachine.insertCoin(1); // Insufficient amount

        assertThrows(IllegalArgumentException.class, () -> vendingMachine.purchaseProduct(id));
        assertEquals(1, vendingMachine.getBalance());
        assertEquals(initialQty, product.getQuantity());
    }

    // Test Case 5 — Purchase Fails (Out of Stock)
    @Test
    void testPurchaseFailsOutOfStock() {
        Product product = findProductByName("Chips");
        assertNotNull(product);
        String id = product.getId();
        
        // Manually set quantity to 0 for testing
        product.setQuantity(0);

        vendingMachine.insertCoin(50); // Enough money

        assertThrows(IllegalArgumentException.class, () -> vendingMachine.purchaseProduct(id));
        assertEquals(50, vendingMachine.getBalance());
        assertEquals(0, product.getQuantity());
    }

    // Test Case 6 — Return Change Resets Balance
    @Test
    void testReturnChangeResetsBalance() {
        vendingMachine.insertCoin(50);
        int change = vendingMachine.returnChange();
        assertEquals(50, change);
        assertEquals(0, vendingMachine.getBalance());
    }

    // Test Case 7 — Get Products Returns All Items
    @Test
    void testGetProductsReturnsAllItems() {
        List<Product> products = vendingMachine.getProducts();
        assertNotNull(products);
        assertEquals(5, products.size()); // We initialize 5 products
    }

    // Additional Test: Get Accepted Coins
    @Test
    void testGetAcceptedCoins() {
        List<Integer> accepted = vendingMachine.getAcceptedCoins();
        assertTrue(accepted.contains(1));
        assertTrue(accepted.contains(5));
        assertTrue(accepted.contains(10));
        assertTrue(accepted.contains(20));
        assertTrue(accepted.contains(50));
        assertFalse(accepted.contains(3));
    }

    // Helper to find product by name (needed because IDs are dynamic static counters)
    private Product findProductByName(String name) {
        for (Product p : vendingMachine.getProducts()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }
}