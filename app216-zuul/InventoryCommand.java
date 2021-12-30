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
        System.out.println(" You are carrying:");
        System.out.println(" " + output + "\n");
    }
}


