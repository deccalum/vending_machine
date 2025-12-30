# Vending Machine
*This project implements a simple vending machine system in Java, featuring various product types and a console-based user interface.*

## **Additions**
  **Product Quantity Management**
    - Added `setQuantity(int quantity)` method in the `Product` class to manage stock levels.

  **Individual Products**
    - Implemented static `idCounter` in `Snack`, `Beverage`, and `Fruit` classes to ensure unique product IDs and added specific attributes (``calories``, ``volume``, ``origin``).

  **Accepted Coins Retrieval**
    - Added `getAcceptedCoins()` method in the `IVendingMachine` interface and its implementation to retrieve the list of accepted coin denominations.

## **Class Diagram**

```mermaid
---
config:
  classDiagram:
    curve: linear
  theme: base

  themeVariables:
    fontFamily: 'monospace'
    fontSize: '14px'
    primaryColor: '#0e0e0eff'
    primaryTextColor: '#ffffffff'
    primaryBorderColor: '#ffffffff'
    lineColor: '#ffffffff'
---

  classDiagram
    class IVendingMachine {
        <<interface>>
        + insertCoin(coin : int) void
        + getBalance() int
        + returnChange() int
        + getProducts() List~Product~
        + purchaseProduct(productId : String) Product
        + getAcceptedCoins() List~int~
    }
    class VendingMachineImpl {
        - acceptedCoins : List~int~
        - balance : int
        - products : List~Product~
        - initializeProducts() void
        
        + insertCoin(coin : int) void
        + getBalance() int
        + returnChange() int
        + getProducts() List~Product~
        + purchaseProduct(productId : String) Product
    }
    class Product {
        <<abstract>>
        - id : String
        - name : String
        - price : int
        - quantity : int

        + getDescription() String
        + setQuantity(quantity : int) void
    }
    class Snack {
      - calories : int
      - static int idCounter$
      + getCalories() int
    }
    class Beverage {
      - volume : int
      - static int idCounter$
      + getVolume() int
    }
    class Fruit {
      - origin : String
      - static int idCounter$
      + getOrigin() String
    }
    class ConsoleUI {
        + start() void
    }

    Product <|-- Snack
    Product <|-- Beverage
    Product <|-- Fruit
    IVendingMachine <|.. VendingMachineImpl
    VendingMachineImpl "1" *-- "*" Product : contains
    ConsoleUI ..> IVendingMachine : uses
```
