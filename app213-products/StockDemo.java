import java.util.*;
/**
 * Demonstrate the StockManager and Product classes.
 * The demonstration becomes properly functional as
 * the StockManager class is completed.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 * 
 * Modified by Liam Smith
 * version 26.10.21
 */
public class StockDemo
{
    // The stock manager.
    public StockList stock;
    // for creating a new product
    public Product product;

    /**
     * Create a StockManager and populate it with at least
     * 10 sample products.
     * @param stock The stock list.
     */
    public StockDemo(StockList stock)
    {
        this.stock = stock;
        
        // Add at least 10 products, they must be unique to you
        // Make sure the ids are sequential numbers
        
        stock.add(new Product(101, "Sony Playstation 5"));
        stock.add(new Product(102, "Apple iPhone 13"));
        stock.add(new Product(103, "Microsoft Xbox Series X"));
        stock.add(new Product(104, "Apple iMac"));
        stock.add(new Product(105, "Optoma UHD42"));
        stock.add(new Product(106, "Korg Prologue"));
        stock.add(new Product(107, "Korg Minilogue XD"));
        stock.add(new Product(108, "Samsung G95T"));
        stock.add(new Product(109, "Microsoft Xbox Series S"));
        stock.add(new Product(110, "Microsoft Flight Simulator"));
    }
    
    /**
     * Provide a demonstration of how the ProductList meets all
     * the user requirements by making a delivery of each product 
     * buying it in various amounts and then selling each
     * product by various amounts. Make sure all the requirements
     * have been demonstrated.
     */
    public void runDemo()
    {
        // Show details of all of the products before delivery.
        stock.print();
        // add 10 unique products
        buyProducts();
        stock.print();
        // sell 10 unique products
        sellProducts();
        stock.print();
        // remove a product
        stock.remove(104);
        stock.print();
        // list all products that start with a phrase
        stock.searchProducts("Korg");
        // list all products with stock below a set level
        stock.printLowStock();
    }
    
    /**
     * Buy 10 products.
     */
    private void buyProducts()
    {
        stock.buyProduct(101, 5);
        stock.buyProduct(102, 6);
        stock.buyProduct(103, 7);
        stock.buyProduct(104, 8);
        stock.buyProduct(105, 9);
        stock.buyProduct(106, 3);
        stock.buyProduct(107, 4);
        stock.buyProduct(108, 1);
        stock.buyProduct(109, 2);
        stock.buyProduct(110, 5);
    }

    /**
     * Sell 10 products.
     */
    private void sellProducts()
    {
        stock.sellProduct(101, 10);
        stock.sellProduct(102, 4);
        stock.sellProduct(103, 3);
        stock.sellProduct(104, 2);
        stock.sellProduct(105, 1);
        // error handling accounts for incorrect id input 1066
        stock.sellProduct(1066, 10);
        stock.sellProduct(107, 2);
        stock.sellProduct(108, 1);
        stock.sellProduct(109, 1);
        stock.sellProduct(110, 4);
    }
    
}