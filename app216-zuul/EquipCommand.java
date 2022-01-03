/**
 * This command allows the player to
 * use an item in the inventory
 *
 * @author Derek Peacock & Nicholas Day
 * @version 2021-08-23
 * 
 * Modified and extended by Liam Smith 27/12/21
 */
public class EquipCommand extends ZuulCommand
{
    String item;
    Item whatItem;
    
    /**
     * Use an item
     */
    public EquipCommand(Game zuul, String item)
    {
        super(zuul);
        this.item = item;
    }    

    public void execute()
    {
        String message;
        boolean carrying = false;

        // set current location
        Location currentLocation = zuul.MAP.getCurrentLocation();

        // set current location in game class
        zuul.locationNow = currentLocation;

        if(item == null) 
        {
            // if there is no second word, we don't know what to use...
            message = "Equip what?";
        }

        else
        {
            // checks if the player has the item in their inventory
            for (int i = 0; i < zuul.player.inventory.size(); i++)
            {
                if (zuul.player.inventory.get(i).getDescription().equals(item))
                {
                    carrying = true;
                    whatItem = zuul.player.inventory.get(i);
                }
            }

            // if the player has the item in their inventory
            if (carrying == true)
            {
                switch(item)
                {
                    case "gasmask":
                        if (zuul.player.gasMask == false) {
                            zuul.player.equipGasMask();
                            message = " gas mask equipped\n Now you can breathe\n";
                        }
                        
                        else
                        {
                            message = " gas mask already equipped\n";
                        }                        
                        break;

                    default:
                        message = "";
                        break;
                  }
            }

            else
            {
                message = " you do not have this item\n";
            }
        }
        // set last line of game class
        zuul.lastLine = message;
    }
}

