/**
 * This command allows the player to
 * use an item in the inventory
 *
 * @author Derek Peacock & Nicholas Day
 * @version 2021-08-23
 * 
 * Modified and extended by Liam Smith 27/12/21
 */
public class CheckCommand extends ZuulCommand
{
    String item;
    Item whatItem;
    
    /**
     * Use an item
     */
    public CheckCommand(Game zuul, String item)
    {
        super(zuul);
        this.item = item;
    }    

    public void execute()
    {
        String message;

        if(item == null) 
        {
            // if there is no second word, we don't know what to check...
            message = "Check what?";
        }

        else
        {
            switch(item)
            {
                case "map":
                    if (zuul.MAP.getCurrentLocation().getShortDescription() == "at the stairs") {

                        System.out.println();
                        System.out.println("     [Janitor Cupboard]<---->[Stairs]<---->[Ladder]<---->[Alley]");
                        System.out.println("                                 |");
                        System.out.println("       [Store Room]<---->[Hospital Ward]<---->[Operating Theatre]<---->[Laboratory]");
                        System.out.println("                                 |");
                        System.out.println("  [Reception Office]<---->[Waiting Room]<---->[Street]");

                        message = zuul.MAP.getCurrentLocation().getShortDescription();
                        break;
                    }

                    else
                    {
                       message = " You are not near a map.";
                       break;
                    }

                case "note":
                    for (int i = 0; i < zuul.player.inventory.size(); i++)
                    {
                        if (zuul.player.inventory.get(i).getDescription().equals(item))
                        {
                            message = " the note reads: 1445";
                            break;
                        }
                    }
                    
                    message = " You do not have this item";               
                    break;

                default:
                    message = " No need to check this.";
                    break;
            }
        }
        System.out.println(message);
    }
}
