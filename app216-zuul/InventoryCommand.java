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
        for(int i = 0; i < zuul.player.inventory.size(); i++)
        {
            output += zuul.player.inventory.get(i).getDescription() + " ";                   
        }
        message += " You are carrying:\n";
        message += " " + output + "\n";
        System.out.println(message);
        zuul.lastLine = message;
    }
}


