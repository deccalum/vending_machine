package lexicon.se;

import java.util.List;

public interface IVendingMachine {
    void insertCoin(int coin);
    int getBalance();
    int returnChange();
    List<Product> getProducts();
    Product purchaseProduct(String productId);
    List<Integer> getAcceptedCoins();

}


