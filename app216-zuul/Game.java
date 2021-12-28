import java.time.Duration;
import java.time.Instant;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  locations, creates the CommandReader and starts the game.  
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 * 
 * Modified and extended by Liam Smith 27/12/21
 */

public class Game 
{
    public final Map MAP;
    private CommandReader reader;
    private boolean gameOver;
    public Player player;
    //public ArrayList<Item> inventory;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        MAP = new Map();
        reader = new CommandReader(this);
        player = new Player();
    }
    
    /**
     * 
     * Main method to run program
     */
    public static void main(String[] args) {
        Game myGame = new Game();
        myGame.play();
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
        gameOver = false;

        // Get time game starts
        Instant start = Instant.now();   

        // Enter the main command loop.  Here we repeatedly 
        // read commands and execute them until the game is over.
                
        while (! gameOver) 
        {
            gameOver = reader.getCommand();
        }

        // Get time game ends
        Instant end = Instant.now();

        // Calculate difference between start time and end time
        Duration timeElapsed = Duration.between(start, end); 

        // Print end game message
        System.out.println("\nThank you for playing.  Good bye.");

        // Print time elapsed
        System.out.println("Time: " + timeElapsed.toMinutes() + ":" + timeElapsed.toSeconds());
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println(" Welcome to the Solo Royale!");
        System.out.println(" Solo Royale is a new game, incredibly boring.");
        System.out.println(" Type 'help' if you need help.");
        System.out.println();
        System.out.println(MAP.getCurrentLocation().getLongDescription());
    }
}
