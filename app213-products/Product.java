/**
 * Model some details of a product sold by a company.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 * 
 * Modified by Liam Smith
 * version 26.10.21
 */
public class Product
{
    // An identifying number for this product.
    public int id;
    // The name of this product.
    public String name;
    // The quantity of this product in stock.
    public int quantity;

    /**
     * Constructor for objects of class Product.
     * The initial stock quantity is zero.
     * @param id The product's identifying number.
     * @param name The product's name.
     */
    public Product(int id, String name)
    {
        this.id = id;
        this.name = name;
        
        quantity = 0;
    }
    
    /**
     * @return The product's id.
     */
    public int getID()
    {
        return id;
    }

    /**
     * @return The product's name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return The quantity in stock.
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * Set the quantity of a product.
     * @param quantity The desired quantity.
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
    /**
     * Increase the quantity of a product.
     * @param amount The desired amount.
     */
    public void increaseQuantity(int amount)
    {
        quantity = quantity + amount;
    }
    
    /**
     * Decrease the quantity of a product.
     * @param amount The desired amount.
     */
    public void decreaseQuantity(int amount)
    {
        if(quantity >= amount)
            quantity = quantity - amount;
    }
    
    /**
     * @return The id, name and quantity in stock.
     */
    public String toString()
    {
        return " ID " + id + ": " +  name + " stock level: " + quantity;
    }

    /**
     * print the product details.
     */
    public void print()
    {
        System.out.println(toString());
    }
}