/**
 * This command allows the player to
 * drop an item and leave it in the
 * current room
 *
 * @author Liam Smith
 * @version 30/12/21
 * 
 */
public class DropCommand extends ZuulCommand
{
    Map map = zuul.MAP;
    Item whatItem;
    String item;
    String message;
    int index;
    boolean carrying = false;
    
    /**
     * Take an item from a location and add it
     * to the player's inventory.
     */
    public DropCommand(Game zuul, String item)
    {
        super(zuul);
        this.item = item;
    }    

    public void execute()
    {

        if(item == null) 
        {
            // if there is no second word, we don't know what to drop...
            message = "Drop what?";
        }

        else
        {
            // check item is in the player's inventory
            for (int i = 0; i < zuul.player.inventory.size(); i++) {
                if (zuul.player.inventory.get(i).getDescription().equals(item)) {
                    carrying = true;
                    index = i;
                }
            }

            if (carrying == true)
            {
                // remove item from player's inventory
                zuul.player.inventory.remove(index);

                // add the item to the current room
                map.getCurrentLocation().setItem(new Item(item));

                // set message
                message = " ";
                message += item + " dropped";
            }

            else
            {
                message = " you do not have this item";
            }
        }

        // Print out a suitable message.
        System.out.println(message);
    }
}

