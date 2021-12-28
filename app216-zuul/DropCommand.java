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
public class DropCommand extends ZuulCommand
{
    String item;
    
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
        Map map = zuul.MAP;
        String message;
        int index = 0;

        if(item == null) 
        {
            // if there is no second word, we don't know what to drop...
            message = "Drop what?";
        }
        else
        {
            // remove item from the player's inventory

            for (int i = 0; i < zuul.player.inventory.size(); i++) {
                if (zuul.player.inventory.get(i).getDescription().equals(item)) {
                    index = i;
                }
            }

            zuul.player.inventory.remove(index);
            message = " " + item + " dropped";

            // add the item to the current room
            map.getCurrentLocation().setItem(new Item(item));
        }

        // Print out a suitable message.
        System.out.println(message);
    }
}

