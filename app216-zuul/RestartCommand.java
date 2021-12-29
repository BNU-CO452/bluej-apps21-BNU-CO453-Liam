import java.io.IOException;

/**
 * This command allows the player to
 * take or pickup an item from a room
 * and carry it around to use somewhere
 * else
 *
 * @author Derek Peacock & Nicholas Day
 * @version 2021-08-23
 * 
 * Modified and extended by Liam Smith 27/12/21
 */
public class RestartCommand
{   
    Game game = new Game();
    /**
     * Restart game
     * @throws IOException
     * @throws InterruptedException
     */

    public void execute() throws IOException, InterruptedException
    {
        game.play();
    }
}
