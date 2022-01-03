/**
 * Starts the "Gas Escape" Application.
 * This class is the main class of the "Gas Escape" application. 
 * "Gas Escape" is a very simple, text based survival game. Players
 * can go to different rooms, pick up and use different items with the
 * goal of escaping.
 * 
 * @author Liam Smith
 * @version 3/1/22
*/

public class Program
{
    private static Game game;

    /**
     * This class contains the main method which
     * is called first, it creates a new Game
     * and calls the main play method
    */
    public static void main(String[] args) throws InterruptedException
    {
        game = new Game();
        game.play();
    }
}