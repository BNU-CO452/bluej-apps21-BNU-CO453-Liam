/**
 * This command allows the player to
 * check an item in the inventory or
 * surrounding area
 *
 * @author Derek Peacock & Nicholas Day
 * @version 2021-08-23
 * 
 * Modified and extended by Liam Smith 30/12/21
 */
public class CheckCommand extends ZuulCommand
{
    String item;
    Item whatItem;
    
    /**
     * Constructor
     */
    public CheckCommand(Game zuul, String item)
    {
        super(zuul);
        this.item = item;
    }    

    /**
     * Check an item
     */
    public void execute()
    {
        // set current location
        Location currentLocation = zuul.MAP.getCurrentLocation();

        // set current location in game class
        zuul.locationNow = currentLocation;

        // message to be displayed to player
        String message = "";

        if(item == null) 
        {
            // if there is no second word, we don't know what to check...
            message = "Check what?\n";
        }

        else
        {
            switch(item)
            {
                case "map":
                    // check if player is in correct location to check this item
                    if (zuul.MAP.getCurrentLocation().getShortDescription() == "at the stairs") {

                        System.out.println("\n     [Janitor Cupboard]<---->[Stairs]<---->[Roof]<---->[Alley]");
                        System.out.println("                                 |");
                        System.out.println("                             [Hallway]");
                        System.out.println("                                 |");
                        System.out.println("       [Store Room]<---->[Hospital Ward]<---->[Operating Theatre]<---->[Laboratory]");
                        System.out.println("                                 |");
                        System.out.println("  [Reception Office]<---->[Waiting Room]<---->[Street]\n");
                        break;
                    }

                    else
                    {
                       message = " You are not near a map.\n";
                       break;
                    }

                case "note":
                    // check if player has item in inventory
                    for (int i = 0; i < zuul.player.inventory.size(); i++)
                    {
                        if (zuul.player.inventory.get(i).getDescription().equals(item))
                        {
                            message = " the note reads: 1445\n";
                            break;
                        }
                    }
                    
                    message = " You do not have this item.\n";               
                    break;

                default:
                    message = " No need to check this.\n";
                    break;
            }
        }
        // set last line in game class
        zuul.lastLine = message;
    }
}
