import java.io.IOException;
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
    private SlowString slow = new SlowString();
    private final int escapeBonus = 1000;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        MAP = new Map();
        reader = new CommandReader(this);
        this.player = new Player();
    }

    /**
     *  Main play routine.  Loops until end of play.
     * @throws IOException
     * @throws InterruptedException
     */
    public void play() throws InterruptedException
    {            
        printWelcome();
        gameOver = false;
        String message = "";

        // Get time game starts
        Instant start = Instant.now();   

        // Enter the main command loop.  Here we repeatedly 
        // read commands and execute them until the game is over.
                
        while (! gameOver && player.health > 0) 
        {
                if(player.checkGasMask() == false)
                {
                    player.gasDamage();
                }

                gameOver = reader.getCommand();

        }

        // Get time game ends
        Instant end = Instant.now();

        // Calculate difference between start time and end time
        Duration timeElapsed = Duration.between(start, end); 

        // Print  winning end game message
        // if(player.isDead)
        // {
        //     message = (" You died.");
        // }

        // time elapsed in minutes
        int minutes = timeElapsed.toMinutesPart();

        // seconds remaining of time elapsed
        int seconds = timeElapsed.toSecondsPart() % 60;

        // leading zero if required for seconds
        String zero = "";
        if(seconds < 10)
        {
            zero = "0";
        }

        // calculate score
        player.score = (timeElapsed.toSecondsPart() + player.health) * 10 ;
        
        // Print time elapsed & score
        System.out.println("\n Time: " + minutes + ":" + zero + seconds + " | Score: " + player.score);

        System.out.println(message + "\n Thank you for playing.  Good bye.\n");
    }

    /**
     * Print out the opening message for the player.
     * @throws InterruptedException
     */
    private void printWelcome() throws InterruptedException
    {
        
        // System.out.println();
        // slow.print( "  ________    _____    _________    ___________ __________________     _____ _____________________", 3);
        // System.out.print("\n");
        // slow.print(" /  _____/   /  _  \\  /   _____/    \\_   _____//   _____/\\_   ___ \\   /  _  \\\\______   \\_   _____/", 3);
        // System.out.print("\n");
        // slow.print("/   \\  ___  /  /_\\  \\ \\_____  \\      |    __)_ \\_____  \\ /    \\  \\/  /  /_\\  \\|     ___/|    __)_ ", 3);
        // System.out.print("\n");
        // slow.print("\\    \\_\\  \\/    |    \\/        \\     |        \\/        \\\\     \\____/    |    \\    |    |        \\", 3);
        // System.out.print("\n");
        // slow.print(" \\______  /\\____|__  /_______  /    /_______  /_______  / \\______  /\\____|__  /____|   /_______  /", 3);
        // System.out.print("\n");
        // slow.print("        \\/         \\/        \\/             \\/        \\/         \\/         \\/                 \\/ ", 3);
        // System.out.print("\n");
        // System.out.println("                                                                              v1.0 By Liam Smith");
        // System.out.println();
        // slow.print(" Welcome to Gas Escape!", 35);
        // Thread.sleep(1000);
        // slow.print(" Type 'help' if you need help.\n", 35);
        // System.out.println();
        // Thread.sleep(1000);

        // slow.print(" You have just woken up.", 35);
        // Thread.sleep(1000);
        // slow.print(" There is gas everywhere. ", 35);
        // Thread.sleep(1000);
        // slow.print("You cannot breathe.", 35);
        // Thread.sleep(1000);
        slow.print(MAP.getCurrentLocation().getLongDescription(), 35);
    }
}
