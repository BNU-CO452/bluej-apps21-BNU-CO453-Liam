/**
 * This shows the values of player attributes such as
 * health and score
 * 
 * @author Liam Smith
 * @version 3/1/22
 */

public class StatusCommand extends ZuulCommand
{
    String message = "";

    public StatusCommand(Game zuul)
    {
        super(zuul);
    }

    public void execute()
    {
        // set current location
        Location currentLocation = zuul.MAP.getCurrentLocation();

        // set current location in game class
        zuul.locationNow = currentLocation;

        message += " Health: " + zuul.player.health + " | Score: " + zuul.player.score + "\n";

        // set last line of game class
        zuul.lastLine = message;
    }
}
