import java.time.Duration;
import java.time.Instant;
import java.lang.System;
import java.io.*;

/**
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This Game class creates and initialises all the others: it creates all
 *  locations, creates the CommandReader, displays a HUD and calculates the player score.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 * 
 * Modified and extended by Liam Smith 27/12/21
 */

public class Game 
{
    public final Map MAP;
    private final String escapeLocation = "the Street";

    private CommandReader reader;
    private Instant start;
    private Instant end;
    private Duration timeElapsed;
    public Player player;
    private SlowString slow = new SlowString();
    public Location locationNow;
    private Location locationAfter;

    private int minutes;
    private int seconds;
    private int escapeBonus;
    private int count = 0;

    public String lastLine = "";
    public String alert = "";
    private String message = "";
    
    private boolean gameOver = false;
    boolean win = false;

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

        getStartTime();   

        // Enter the main command loop.  Here we repeatedly 
        // read commands and execute them until the game is over.     
        while (! gameOver) 
        {
            count++;

            setup();

            checkRoomsReached();

            showHud();

            startGasDamage();

            gameOver = decideContinue();
        }

        getEndTime();

        calculateGameDuration(); 

        String zero = formatGameTime();

        calculateScore();
        
        printGameSummary(message, zero);

        // close program
        System.exit(0);
    }

    /**
     * 
     * @throws InterruptedException
     */
    private boolean decideContinue() throws InterruptedException {
        // if player has not won, get command
        if (checkWin() == false) {
            gameOver = reader.getCommand();

            // get location after command
            locationAfter = MAP.getCurrentLocation();
        }

        else
        {
            // end game
            gameOver = true;
        }

        return gameOver;
    }

    /**
     * start gas damage if gas mask is not on
     */
    private void startGasDamage() {
        if(player.checkGasMask() == false)
        {
            player.gasDamage();
        }
    }

    /**
     * executes on first loop only
     * clears console
     * sets location
     * adds location to player array 
     */
    private void setup() {
        if (count < 2) {
            try {
                clearConsole();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.console().flush();

            locationNow = MAP.getCurrentLocation();

            player.roomsReached.add(locationNow);
        }
    }

    /**
     * checks the rooms a player has reached and adds them 
     * to an array
     */
    private void checkRoomsReached() {

        int match = 0;

        if (match < 1 && locationAfter != null)
        {
            for (int i = 0; i < player.roomsReached.size(); i++)
            {
                if (player.roomsReached.get(i) == locationAfter)
                {
                    match++;
                }
            }
        }

        // add if not, and update score
        if (match < 1  && locationAfter != null) {
            player.roomsReached.add(locationAfter);
            player.score += 100;
        }
    }

    /**
     * Formats the display of the game time
     * @return // zero if required
     */
    private String formatGameTime()
    {
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

    /**
     * Calculates duration of game
     */
    private void calculateGameDuration()
    {
        timeElapsed = Duration.between(start, end);
    }

    /**
     * Print game summary
     * @param message // variable output
     * @param zero // leading zero
     * @throws InterruptedException
     */
    private void printGameSummary(String message, String zero) throws InterruptedException
    {
        // if player won, print additional message
        if (win) {
            printWinMessage();
        }

        // Print time elapsed & score
        System.out.println("\n Time: " + minutes + ":" + zero + seconds + " | Score: " + player.score);

        System.out.println(message + "\n Thank you for playing.  Good bye.\n");
    }

    /**
     * Calculate score
     * @throws InterruptedException
     */
    private void calculateScore() throws InterruptedException
    {

        if (checkWin() == true) {
            // player remaining health
            for (int i = 0; i < player.health; i++)
            {
                player.score += 10;
            }
        }

        // // how many rooms did a player reach
        for (int i = 1; i < player.roomsReached.size(); i++) {
            player.score += 100;
        }

        player.score += escapeBonus;
    }

    /**
     * Get time the game ended
     */
    private void getEndTime()
    {
        end = Instant.now();
    }

    /**
     * Get time the game started
     */
    private void getStartTime()
    {
        start = Instant.now();
    }

    // Check if player won the game
    private boolean checkWin() throws InterruptedException
    {

        if (MAP.getCurrentLocation().getShortDescription().equals(escapeLocation))
        {
            // do we need to print here?
            //printWinMessage();
            win = true;
        }

        return win;
    }

    // Print winning message
    private void printWinMessage() throws InterruptedException
    {
        // bonus points for winning
        escapeBonus = 1000;

        slow.print("\n Well done. You survived.", 25);
        Thread.sleep(2000);

        slow.print("\n For now...\n", 130);
        Thread.sleep(1000);

        System.out.println();
        System.out.println(" Level 1 Completed");
    }

    /**
     * Print out the heading and opening message for the player.
     * @throws InterruptedException
     */
    private void printWelcome() throws InterruptedException
    {
        try {
            clearConsole();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.console().flush();
        
        System.out.println();
        slow.print( "  ________    _____    _________    ___________ __________________     _____ _____________________", 3);
        System.out.print("\n");
        slow.print(" /  _____/   /  _  \\  /   _____/    \\_   _____//   _____/\\_   ___ \\   /  _  \\\\______   \\_   _____/", 3);
        System.out.print("\n");
        slow.print("/   \\  ___  /  /_\\  \\ \\_____  \\      |    __)_ \\_____  \\ /    \\  \\/  /  /_\\  \\|     ___/|    __)_ ", 3);
        System.out.print("\n");
        slow.print("\\    \\_\\  \\/    |    \\/        \\     |        \\/        \\\\     \\____/    |    \\    |    |        \\", 3);
        System.out.print("\n");
        slow.print(" \\______  /\\____|__  /_______  /    /_______  /_______  / \\______  /\\____|__  /____|   /_______  /", 3);
        System.out.print("\n");
        slow.print("        \\/         \\/        \\/             \\/        \\/         \\/         \\/                 \\/ ", 3);
        System.out.print("\n");
        slow.print("                                                                               v1.0 By Liam Smith", 4);
        System.out.println();

        slow.print(" Welcome to Gas Escape!", 35);
        Thread.sleep(1000);

        slow.print(" Type 'help' if you need help.\n", 35);
        System.out.println();
        Thread.sleep(4000);

        try {
            clearConsole();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.console().flush();

        slow.print(" You have just woken up.", 35);
        Thread.sleep(1000);

        slow.print(" There is gas everywhere. ", 35);
        Thread.sleep(1000);

        slow.print("You cannot breathe.", 35);
        Thread.sleep(2000);

        try {
            clearConsole();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.console().flush();
    }

    // show useful info to the player
    private void showHud() throws InterruptedException
    {
        // if player has not changed location and not dead
        if(locationNow != locationAfter && player.isDead == false)
        {
            try {
                clearConsole();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.console().flush();

            // if player is not at the winning location
            if (! MAP.getCurrentLocation().getShortDescription().equals("the Street"))
            {
                slow.print(alert + "\n\n", 35);
                slow.print(MAP.getCurrentLocation().getLongDescription(), 35);
                alert = "";
            }
        }

        else if(locationAfter == null)
        {
            slow.print(MAP.getCurrentLocation().getLongDescription(), 35);
        }

        slow.print(lastLine + "\n", 35);
        lastLine = "";
    }

    // clear the console
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
