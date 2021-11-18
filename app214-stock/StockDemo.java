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
     */
    public StockDemo(StockList stock)
    {
        this.stock = stock;
        
        // Add at least 10 products, they must be unique to you
        // Make sure the ids are sequential numbers
        
        stock.add(new Product(100, "Sony Playstation 5"));
        stock.add(new Product(101, "Apple iPhone 13"));
        stock.add(new Product(102, "Microsoft Xbox Series X"));
        stock.add(new Product(103, "Apple iMac"));
        stock.add(new Product(104, "Optoma UHD42"));
        stock.add(new Product(105, "Korg Prologue"));
        stock.add(new Product(106, "Korg Minilogue XD"));
        stock.add(new Product(107, "Samsung G95T"));
        stock.add(new Product(108, "Microsoft Xbox Series S"));
        stock.add(new Product(109, "Microsoft Flight Simulator"));

        buyProducts();
        System.out.println("Demo products added\n");
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
    
    private void buyProducts()
    {
        stock.buyProduct(100, 5);
        stock.buyProduct(101, 6);
        stock.buyProduct(102, 7);
        stock.buyProduct(103, 8);
        stock.buyProduct(104, 9);
        stock.buyProduct(105, 3);
        stock.buyProduct(106, 4);
        stock.buyProduct(107, 1);
        stock.buyProduct(108, 2);
        stock.buyProduct(109, 5);
    }

    private void sellProducts()
    {
        stock.sellProduct(100, 10);
        stock.sellProduct(101, 4);
        stock.sellProduct(102, 3);
        stock.sellProduct(103, 2);
        stock.sellProduct(104, 1);
        // error handling accounts for incorrect id input 1066
        stock.sellProduct(1055, 10);
        stock.sellProduct(106, 2);
        stock.sellProduct(107, 1);
        stock.sellProduct(108, 1);
        stock.sellProduct(109, 4);
    }
    
}