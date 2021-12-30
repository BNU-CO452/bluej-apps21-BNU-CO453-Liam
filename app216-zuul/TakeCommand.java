/**
 * This command allows the player to
 * use an item in the inventory
 *
 * @author Derek Peacock & Nicholas Day
 * @version 2021-08-23
 * 
 * Modified and extended by Liam Smith 27/12/21
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
        String whatItem = "";
        String message;

        if(item == null) 
        {
            // if there is no second word, we don't know what to use...
            message = "Take what?";
        }

        else
        {

            for (int i = 0; i < map.getCurrentLocation().items.size(); i++)
            {
                if (map.getCurrentLocation().items.get(i).description.equals(item))
                {
                    here = true;
                    whatItem = map.getCurrentLocation().items.get(i).description;
                }
            }

            if (here == true)
            {
                // and add it to the player's inventory
                //whatItem = map.getCurrentLocation().getItem(item).description;
                zuul.player.inventory.add(new Item(whatItem));
                message = " " + whatItem + " added to inventory\n";

                // remove the item from the current room
                map.getCurrentLocation().removeItem(whatItem);
            }

            else
            {
                message = " this item is not here\n";
            }
        }
        System.out.println(message);
    }
}