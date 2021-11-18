import java.util.ArrayList;

/**
 * Manage the stock in a business.
 * The stock is described by zero or more Products.
 * 
 * @author Liam Smith 
 * @version 18.11.21
 */
public class StockList
{
    // the minimum amount of stock to be considered as low
    public final int MINIMUM_QUANTITY = 5;
    
    // A list of the products.
    public ArrayList<Product> stock;

    /**
     * Initialise the stock manager.
     */
    public StockList()
    {
        stock = new ArrayList<Product>();
    }

    /**
     * Add a product to the list.
     * @param item The product item to be added.
     */
    public void add(Product item) {
        if(stock.contains(item)) {
            System.out.println(" Error: item already added: " + item);
        }

        else {
            stock.add(item);
        }
    }
    
    /**
     * remove a product from the list.
     * @param productID The product item to be added.
     */
    public void remove(int productID) {
        Product product = findProduct(productID);
            
        if(product != null) {
            stock.remove(product);
            System.out.println("\n Product with ID " + productID + " has been removed\n");
        }
            
        else {
            System.out.println("\n No product match found\n");
        }     
    }
    
    /**
     * A method to buy a single quantity of the product
     * @param product ID The ID of the product
     */
    public void buyProduct(int productID) {
        buyProduct(productID, 1);
    }

    /**
     * Buy a quantity of a particular product.
     * Increase the quantity of the product by the given amount.
     * @param productID The ID of the product.
     * @param amount The amount to increase the quantity by.
     */
    public void buyProduct(int productID, int amount) {
        Product product = findProduct(productID);
        checkQuantity(amount);

            if(checkQuantity(amount) == true && product != null) {
                product.setQuantity(amount);

                System.out.println();
                System.out.println(" Item purchased: " + product.name);
                System.out.println(" Quantity: " + amount);
            }

            else {
                System.out.println(" No products match: " + productID);
            }
    }

    /**
     * Checks the quantity is valid before buying or selling products.
     * @param quantity The quantity to check.
     */
    public boolean checkQuantity(int quantity) {

        boolean valid;

        if(quantity > 0 && quantity < 11) {
            valid = true;
        }

        else {
            System.out.println(" Quantity must be be in the range of 1-10");
            valid = false;
        }
        return valid;
    }

    /**
     * Find a product to match the product id,
     * if not found return null
     * @param x The product to be returned
     */
    public Product findProduct(int productID) {
        Product x = null;
        
        for(Product product : stock){

            if(productID == product.id){
                x = product;
                return x;
            }

            else {
                x = null;
            }
        }
        return x; 
    }

    /**
     * Sell one of the given product.
     * Show the before and after status of the product.
     * @param productID The ID of the product being sold.
     */
    public void sellProduct(int productID) {
        Product product = findProduct(productID);
        
        if(product != null) {
            System.out.println(" product: " + product.name);
            System.out.println(" product quantity: " + product.quantity);
        
            if(product.getQuantity() > 0) {
                product.decreaseQuantity(1);

                System.out.println();
                System.out.println(" product: " + product.name);
                System.out.println(" product quantity: " + product.quantity);
            }

            else {
                System.out.println();
                System.out.println(" Error: insufficient stock available");
                System.out.println(" product: " + product.name);
                System.out.println(" product quantity: " + product.quantity);
            }
        }

        else {
            System.out.println(" No product found with ID: " + productID);
        }
    }    
    
    /**
     * Sell specific quantities of a product
     * @param productID The ID of the product.
     * @param amount The amount to sell.
     */
    public void sellProduct(int productID, int amount) {
        Product product = findProduct(productID);
        checkQuantity(amount);
        
        if(checkQuantity(amount) == true && product != null) {

            if(product.quantity >= amount) {
                product.decreaseQuantity(amount);

                System.out.println();
                System.out.println(" Item sold: " + product.name);
                System.out.println(" Quantity: " + amount);
            }

            else {
                System.out.println();
                System.out.println(" Error: insufficient stock available");
                System.out.print(product.toString());
                System.out.println(" quantity to sell: " + amount);
            }
        }

        else {
            System.out.println();
            System.out.println(" No products match: " + productID);
        }
    }
    
    /**
     * Locate a product with the given ID, and return how
     * many of this item are in stock. If the ID does not
     * match any product, return zero.
     * @param productID The ID of the product.
     * @return The quantity of the given product in stock.
     */
    public int numberInStock(int productID)
    {
        Product product = findProduct(productID);

        if(product != null) {
            return product.quantity;
        }

        else {
            return 0;
        }
    }

    /**
     * Print details of the given product. If found,
     * its name and stock quantity will be shown.
     * @param productID The ID of the product to look for.
     */
    public void printProduct(int productID)
    {
        Product product = findProduct(productID);
        
        if(product != null) {
            System.out.println(product.toString());
        }
    }
    
    /**
     * Print out each product in the stock
     * in the order they are in the stock list
     */
    public void print()
    {
        printHeading();
        
        for(Product product : stock) {
            System.out.println(product);
        }

        System.out.println();
    }
    
    /**
     * Print out a list of products that are low in stock
     */
    public void printLowStock() {
        System.out.println();
        System.out.println(" Low stock items:");
        System.out.println();
        
        for(Product product : stock){
            if(product.getQuantity() >= 1 && product.getQuantity() <= MINIMUM_QUANTITY){
                product.print();
            }
        }
    }

    /**
     * Print a list of low stock products of a specific quantity
     * @param quantity The quantity of product
     */
    public void printLowStock(int quantity) {

        for(Product product : stock){

            if(product.getQuantity() == quantity){
                product.print();
            }
        }
    }

    /**
     * Re-stock items below minimum stock level.
     */
    public void autoReStock() {
        int i = 0;
        for(Product product : stock){
            
            i = product.quantity;
            
            do{
                buyProduct(product.id, 1);
                product.increaseQuantity(i);
                i++;
            }
            while(i < MINIMUM_QUANTITY);
        }
        System.out.println(" All stock has been replenished to minimum levels");
    }
    
    /**
     * Sell all available stock.
     */
    public void sellAllStock() {
        for(Product product : stock){
            sellProduct(product.id, product.quantity);

            System.out.println();
        }
        System.out.println(" All stock has been sold");
    }
    
    /**
     * Search products for a partial match of a string.
     * @param name The name of the product.
     */
    public void searchProducts(String name) {
        System.out.println(" Searching for: " + name);
        System.out.println();
        
        for(Product product : stock) {
            if(product.getName().toUpperCase().contains(name.toUpperCase())) {
                System.out.println(product.toString());
            }
        }
    }

    /**
     * Print a heading of the stock list
     */
    public void printHeading()
    {
        System.out.println();
        System.out.println(" Liam's Stock List");
        System.out.println(" ====================");
        System.out.println();
    }
}