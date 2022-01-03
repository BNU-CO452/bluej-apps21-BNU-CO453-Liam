/**
 * This command allows the player to
 * use an item in the inventory
 *
 * @author Derek Peacock & Nicholas Day
 * @version 2021-08-23
 * 
 * Modified and extended by Liam Smith 3/1/22
 */
public class TakeCommand extends ZuulCommand
{
    String item;
    Item whatItem;
    String message;
    boolean here = false;
    
    /**
     * Use an item
     */
    public TakeCommand(Game zuul, String item)
    {
        super(zuul);
        this.item = item;
    }    

    public void execute()
    {
        Map map = zuul.MAP;
        String itemDescription = "";
        String message;

        // set current location
        Location currentLocation = map.getCurrentLocation();

        // set current location in game class
        zuul.locationNow = currentLocation;

        if(item == null) 
        {
            // if there is no second word, we don't know what to use...
            message = "Take what?";
        }

        else
        {

            for (int i = 0; i < map.getCurrentLocation().items.size(); i++)
            {
                if (map.getCurrentLocation().items.get(i).description.equals(item) ||
                map.getCurrentLocation().items.get(i).description.contains(item))
                {
                    here = true;

                    whatItem = map.getCurrentLocation().items.get(i);
                    itemDescription = whatItem.description;               
                }
            }

            if (here == true)
            {
                if(itemDescription == "ladder" || itemDescription == "keypad" || itemDescription == "map")
                {
                    message = " this item cannot be taken\n";
                }

                else
                {
                    // and add it to the player's inventory

                    zuul.player.inventory.add(new Item(itemDescription, whatItem.value));
                    message = " " + itemDescription + " added to inventory\n";

                    // remove the item from the current room
                    map.getCurrentLocation().removeItem(itemDescription);
                }
            }

            else
            {
                message = " this item is not here\n";
            }
        }
        // set last line of game class
        zuul.lastLine = message;
    }
}