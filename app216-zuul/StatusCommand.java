/**
 * add comment
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
        Location currentLocation = zuul.MAP.getCurrentLocation();

        // set current location
        zuul.locationNow = currentLocation;

        message += " Health: " + zuul.player.health + " | Score: " + zuul.player.score + "\n";
        zuul.lastLine = message;
    }
}
