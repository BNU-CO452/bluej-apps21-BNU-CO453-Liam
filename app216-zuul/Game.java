import java.time.Duration;
import java.time.Instant;
import java.lang.System;
import java.io.*;

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
    private Instant start;
    private Instant end;
    private int minutes;
    private int seconds;
    private Duration timeElapsed;
    private boolean gameOver;
    public Player player;
    private SlowString slow = new SlowString();
    private int escapeBonus;
    private final String escapeLocation = " in the Street";
    private int count = 0;
    public String lastLine = "";
        
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
     * @throws InterruptedException
     */
    public void play() throws InterruptedException
    {            
        printWelcome();

        gameOver = false;

        String message = "";

        // Get time game starts
        getStartTime();   

        // Enter the main command loop.  Here we repeatedly 
        // read commands and execute them until the game is over.     
        while (! gameOver && player.health > 0) 
        {
            count++;

            // delays console clear on first loop
            if(count == 1)
            {
                Thread.sleep(5000);
            }

            // clear console
            // try {
            //     clearConsole();
            // } catch (IOException e) {
            //     e.printStackTrace();
            // }

            // System.console().flush();

            // if player is not wearing gas mask. inflict damage
            if(player.checkGasMask() == false)
            {
                player.gasDamage();
            }

            // show useful info
            //showHud();

            // check if player has won
            checkWin();

            gameOver = reader.getCommand();
        }

        // Get time game ends
        getEndTime();

        // Calculate difference between start time and end time
        calculateGameDuration(); 

        String zero = formatGameTime();

        calculateScore();
        
        printGameSummary(message, zero);
    }

    private String formatGameTime() {
        // time elapsed in minutes
        minutes = timeElapsed.toMinutesPart();

        // seconds remaining of time elapsed
        seconds = timeElapsed.toSecondsPart() % 60;

        // leading zero if required for seconds
        String zero = "";
        if(seconds < 10)
        {
            zero = "0";
        }
        return zero;
    }

    private void calculateGameDuration() {
        timeElapsed = Duration.between(start, end);
    }

    private void printGameSummary(String message, String zero) {
        // Print time elapsed & score
        System.out.println("\n Time: " + minutes + ":" + zero + seconds + " | Score: " + player.score);

        System.out.println(message + "\n Thank you for playing.  Good bye.\n");
    }

    private void calculateScore() {
        // calculate score
        player.score = ((timeElapsed.toSecondsPart() + player.health) * 10) + escapeBonus;
    }

    private void getEndTime() {
        end = Instant.now();
    }

    private void getStartTime() {
        start = Instant.now();
    }

    // checks if player won the game
    private void checkWin() throws InterruptedException {
        if (MAP.getCurrentLocation().getShortDescription().equals(escapeLocation))
        {
            printWinMessage();
        }
    }

    // only print this message if player has won the game
    private void printWinMessage() throws InterruptedException {
        // bonus points for winning
        escapeBonus = 1000;

        slow.print("\n Well done. You survived.", 25);
        Thread.sleep(2000);

        slow.print("\n For now...\n", 130);
        Thread.sleep(1000);

        System.out.println();
        System.out.println(" Level 1 Completed");
        System.out.println(" Type \"quit\" and press Enter to end game.");

        gameOver = true;
    }

    /**
     * Print out the heading and opening message for the player.
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
        // System.out.println("                                                                               v1.0 By Liam Smith");
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

    // show useful info to the player
    private void showHud()
    {
        System.out.println("health: " +  player.health + " | armour: " + player.armour + " | gas mask: " );
        System.out.println(MAP.getCurrentLocation().getLongDescription());

        System.out.println(lastLine);
        lastLine = "";
    }

    // clear the console after each command
    private void clearConsole() throws IOException
    {
        ProcessBuilder pb = new ProcessBuilder("clear");
        Process startProcess = pb.inheritIO().start();

        try {
            startProcess.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
