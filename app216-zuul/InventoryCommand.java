/**
 * Write a description of class HelpCommand here.
 *
 * @author Liam Smith
 * @version 27/12/21
 */
public class InventoryCommand extends ZuulCommand
{
    public InventoryCommand(Game zuul)
    {
        super(zuul);
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and
     * a list of the command words.
     */
    public void execute()
    {
        String output = "";

        for(int i = 0; i < zuul.player.inventory.size(); i++)
        {
            output += zuul.player.inventory.get(i).getDescription() + " ";                   
        }
        System.out.println(" You are carrying:");
        System.out.println(" " + output);
    }
}


