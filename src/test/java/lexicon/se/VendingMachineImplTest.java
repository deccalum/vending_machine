package lexicon.se;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineImplTest {
    VendingMachineImpl vendingMachine = new VendingMachineImpl();
    

    @Test
    void insertCoin() {
        vendingMachine.insertCoin(10);
        vendingMachine.insertCoin(30);
        vendingMachine.insertCoin(10);
        vendingMachine.insertCoin(50);
    }

    @Test
    void getBalance() {
    }

    @Test
    void purchaseProduct() {

        // The assertEquals commands verify that actual values match expected values. If
        // they don't match, the test fails.


        assertEquals(0, vendingMachine.getBalance());

        vendingMachine.insertCoin(50);
        Product product = vendingMachine.purchaseProduct(2);

        assertNotNull(product);
        assertEquals(0, vendingMachine.getBalance());
        assertEquals(2, product.getQuantity());

    }

    @Test
    void returnChange() {
    }

    @Test
    void getProducts() {
    }
}