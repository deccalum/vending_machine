package lexicon.se;

import java.util.List;

public interface IVendingMachine {
    void insertCoin(int coin);
    int getBalance();

    void purchaseProduct(int productId, int balance);

    int returnChange();
    List<Product> getProducts();

}


