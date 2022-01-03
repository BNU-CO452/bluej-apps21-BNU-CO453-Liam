/**
 * This class displays the items that a player
 * has picked up and added to their inventory
 *
 * @author Liam Smith
 * @version 30/12/21
 */
public class InventoryCommand extends ZuulCommand
{
    public String output = "";
    public String message = "";

    public InventoryCommand(Game zuul)
    {
        super(zuul);
    }

    /**
     * Print a list of items in the player's inventory
     */
    public void execute()
    {
        Location currentLocation = zuul.MAP.getCurrentLocation();
        // set current location
        zuul.locationNow = currentLocation;

        for(int i = 0; i < zuul.player.inventory.size(); i++)
        {
            output += zuul.player.inventory.get(i).getDescription() + " ";                   
        }
        message += "\n You are carrying:\n";
        message += " " + output + "\n";

        // set last line of game class
        zuul.lastLine = message;
    }
}


