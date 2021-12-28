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
public class TakeCommand extends ZuulCommand
{
    String item;
    
    /**
     * Take an item from a location and add it
     * to the player's inventory.
     */
    public TakeCommand(Game zuul, String item)
    {
        super(zuul);
        this.item = item;
    }    

    public void execute()
    {
        Map map = zuul.MAP;
        String whatItem;
        String message;

        if(item == null) 
        {
            // if there is no second word, we don't know what to take...
            message = "Take what?";
        }
        else
        {
            // and add it to the player's inventory
            whatItem = map.getCurrentLocation().getItem(item).description;
            zuul.player.inventory.add(new Item(whatItem));
            message = " " + whatItem + " added to inventory";

            // remove the item from the current room
            map.getCurrentLocation().removeItem(whatItem);
        }

        // Print out a suitable message.
        System.out.println(message);
    }
}
