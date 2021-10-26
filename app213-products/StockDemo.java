
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

    /**
     * Create a StockManager and populate it with at least
     * 10 sample products.
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

        buyProducts();
        stock.print();        

        sellProducts();
        stock.print();
        
        
    }
    
    private void buyProducts()
    {
        stock.buyProduct(101, 50);
        stock.buyProduct(102, 60);
        stock.buyProduct(103, 70);
        stock.buyProduct(104, 80);
        stock.buyProduct(105, 90);
        stock.buyProduct(106, 30);
        stock.buyProduct(107, 40);
        stock.buyProduct(108, 12);
        stock.buyProduct(109, 55);
        stock.buyProduct(110, 35);
    }

    private void sellProducts()
    {
        stock.sellProduct(101, 30);
        stock.sellProduct(102, 70);
        stock.sellProduct(103, 80);
        stock.sellProduct(104, 99);
        stock.sellProduct(105, 18);
        stock.sellProduct(1066, 30);
        stock.sellProduct(107, 22);
        stock.sellProduct(108, 13);
        stock.sellProduct(109, 3);
        stock.sellProduct(110, 30);
    }
    
}