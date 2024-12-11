/**
 * This prints the characters of a string one at a time with 
 * a variable speed
 * @author Liam Smith
 * @version 29/12/21
 */

public class SlowString {
    int index;

    // print a string
    public void print(String string, int speed) throws InterruptedException
    {
        for (index = 0; index < string.length(); index++) {

            System.out.print(string.charAt(index));

            // time delay
            Thread.sleep(speed);
        }
    }
}
