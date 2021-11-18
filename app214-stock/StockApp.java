/**
 * This app provides a user interface to the
 * stock manager so that users can add, edit,
 * print and remove stock products
 *
 * @author Liam Smith
 * @version 0.1
 */
public class StockApp
{
    private InputReader reader;
    private StockList stock;
    
    /**
     * Constructor for objects of class StockApp.
     */
    public StockApp()
    {
        reader = new InputReader();
        stock = new StockList();
        StockDemo demo = new StockDemo(stock);
    }

    /**
     *  Display a list of menu choices and execute
     *  the user's choice.
     */
    public void run()
    {
        boolean finished = false;
        
        while(!finished)
        {
            printHeading();
            printMenuChoices();

            String choice = reader.getString("Please enter your choice > ");

            finished = executeChoice(choice.toLowerCase());
        }
    }

    /**
     * Executes the option chosen by the user.
     * @param choice The string value of the users input.
     * @return true when user is finished.
     */
    private boolean executeChoice(String choice)
    {
        switch (choice) {
            case "add": {
                addProduct(validateProduct());
                break;
            }

            case "remove":
                removeProduct();
                break;

            case "buy": {
                buyProduct();
                break;
            }

            case "sell": {
                sellProduct();
                break;
            }

            case "search":
                searchProduct();
                break;

            case "low stock": {
                stock.printLowStock();
                break;
            }

            case "re-stock": {
                reStock();
                break;
            }

            case "print":
                stock.print();
                break;

            case "quit":
                return true;

            default:
                break;
        }
        return false;
    }

    /**
     * Re stocks all products to a set minimum quantity.
     */
    private void reStock() {
        String inputQuantity = reader.getString("Enter minimum quantity > ");
        int x = Integer.parseInt(inputQuantity);
        stock.reStock(x);
    }

    /**
     * Remove a product by ID.
     */
    private void removeProduct() {
        String choice;
        choice = reader.getString("Enter ID of product > ");
        stock.remove(Integer.parseInt(choice));
    }

    /**
     * Buy a product by a chosen ID nad quantity.
     */
    private void buyProduct() {
        String inputId = reader.getString("Enter ID of product > ");
        int productId = Integer.parseInt(inputId);

        String inputQuantity = reader.getString("Enter quantity of product > ");
        int quantity = Integer.parseInt(inputQuantity);

        stock.buyProduct(productId, quantity);
    }

    /**
     * Sell a product by a chosen ID nad quantity.
     */
    private void sellProduct() {
        String inputId = reader.getString("Enter ID of product > ");
        int productId = Integer.parseInt(inputId);

        String inputQuantity = reader.getString("Enter quantity of product > ");
        int quantity = Integer.parseInt(inputQuantity);

        stock.sellProduct(productId, quantity);
    }

    /**
     * Check if a product already exists in the stock list with a matching ID or Name.
     * @return
     */
    private Product validateProduct() {
        String inputId = reader.getString("Enter ID of product > ");
        int productId = Integer.parseInt(inputId);

        String productName = reader.getString("Enter name of product > ");

        if (stock.stock.size() > 0) {

            boolean exists = false;

            for (Product x : stock.stock) {

                if (x.id == productId) {
                    exists = true;
                    System.out.println("\n Error: A product with ID: " + productId + " already exists!");
                }

                if (x.name.equals(productName)) {
                    exists = true;
                    System.out.println("\n Error: A product with Name: " + productName + " already exists!");
                }
            }

            if (!exists) {
                return new Product(productId, productName);
            }
        } else {
            return new Product(productId, productName);
        }
        return null;
    }

    /**
     * Search for a product with a partial match of a string
     */
    private void searchProduct() {
        String name = reader.getString("Enter name of product > ");
        System.out.println();

        if (stock.stock.size() > 0) {

            boolean match = false;

            for (Product product : stock.stock) {

                if (product.name.toLowerCase().contains(name.toLowerCase())){
                    System.out.println("ID: " + product.id + "\t" + product.name);
                    match = true;
                }
            }

            if (!match) {
                System.out.println("No match found.");
            }
        }

        else {
            System.out.println("No products in stock list");
        }
    }

    /**
     * Add a product to the stock list if not equal to null.
     * @param product The product to be added.
     */
    private void addProduct(Product product) {
        if (product != null){
            stock.add(product);
            System.out.println("\nProduct added: " + product + "\n");
        }

        else {
            System.out.println(" Error: Cannot add this product\n");
        }
    }

    /**
     * Print out a menu of operation choices
     */
    private void printMenuChoices()
    {
        System.out.println();
        System.out.println("    Add:        Add a new product");
        System.out.println("    Remove:     Remove an old product");
        System.out.println("    Buy:        Buy a product");
        System.out.println("    Sell:       Sell a product");
        System.out.println("    Search:     Search for a product");
        System.out.println("    Low Stock:  Show low stock products");
        System.out.println("    Re-stock:   Re stock all products to a specific quantity");
        System.out.println("    Print:      Print all products");
        System.out.println("    Quit:       Quit the program");
        System.out.println();        
    }
    
    /**
     * Print the title of the program and the authors name
     */
    private void printHeading()
    {
        System.out.println("********************************");
        System.out.println("  App21-04: Stock Application ");
        System.out.println("      by Liam Smith");
        System.out.println("********************************");
    }
}