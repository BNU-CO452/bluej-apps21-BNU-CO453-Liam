import java.util.*;
/**
 * Write a description of class Module here.
 *
 * @author Liam Smith
 * @version version 1.0 05/10/2021
 */
public class Module
{
    // instance variables - replace the example below with your own
    public String code;
    public String title;
    public int creditValue;

    /**
     * Constructor for objects of class Module
     */
    public Module(String code, String title)
    {
        this.code = code;
        this.title = title;
        this.creditValue = 0;// initialise instance variables
        
    }
    
    public void setCreditValue(int creditValue)
    {
        this.creditValue = creditValue;
    }

    /**
     * Print the Module information
     *
     */
    public void printModuleInfo()
    {
        
        System.out.println();
        System.out.println(" Module Code: " + code);
        System.out.println(" Title: " + title);
        System.out.println(" Credit Value: " + creditValue);
        System.out.println();
    }
}
