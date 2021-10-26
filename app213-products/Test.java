import java.util.ArrayList;
/**
 * Write a description of class Test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Test
{
    // instance variables - replace the example below with your own
    private StockList stockList;
    private StockDemo stockDemo;
    private ArrayList<Product> products;
    /**
     * Constructor for objects of class Test
     */
    public Test()
    {
        // initialise instance variables
        stockList = new StockList();
        
        products = new ArrayList<Product>();
        
        new StockDemo(stockList);
        
        
        
        
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void findProduct(int id)
    {
        for(int i = 0; i < products.size(); i++){
                System.out.println(products.get(i));     
        }
    }
}
