/**
 * This command allows the player to
 * use an item in the inventory
 *
 * @author Derek Peacock & Nicholas Day
 * @version 2021-08-23
 * 
 * Modified and extended by Liam Smith 27/12/21
 */
public class UseCommand extends ZuulCommand
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    
    Item whatItem;

    int index;
    String item;
    String message = "";
    boolean carrying = false;

    SlowString slow = new SlowString();
    
    /**
     * Use an item
     */
    public UseCommand(Game zuul, String item)
    {
        super(zuul);
        this.item = item;
    }    

    public void execute() throws InterruptedException
    {
        // set current location
        Location currentLocation = zuul.MAP.getCurrentLocation();

        // set current location in game class
        zuul.locationNow = currentLocation;

        if(item == null) 
        {
            // if there is no second word, we don't know what to use...
            message = "Use what?";
        }

        else
        {

            for (int i = 0; i < zuul.player.inventory.size(); i++)
            {
                if (zuul.player.inventory.get(i).getDescription().contains(item))
                {
                    carrying = true;
                    whatItem = zuul.player.inventory.get(i);
                    index = i;
                }
            }

            if (carrying == true)
            {
                switch(whatItem.description)
                {
                    case "torch":
                        // do we need this?
                        zuul.MAP.setStatus("in the hallway", "light");

                        // show items in area now
                        message += "\nItems in the area: \n ";
                        for (int i = 0; i < zuul.MAP.getCurrentLocation().items.size(); i++) {
                            message += zuul.MAP.getCurrentLocation().items.get(i).description + 
                            " ";
                        }

                        message += "\n";
                        break;

                    case "key":
                        if (zuul.MAP.getCurrentLocation().getShortDescription().equals("in the waiting room"))
                        {
                            // set room status to open
                            zuul.MAP.setStatus("in the reception office", "open");

                            message = " the reception office is open\n";
                        }
                        else 
                        {
                            message = " cannot use this here\n";
                        }
                        break;

                    case "fire extinguisher":
                        if (zuul.MAP.getCurrentLocation().getShortDescription().equals("at the stairs"))
                        {
                            // set room status to open
                            zuul.MAP.setStatus("at the roof", "open");

                            message = " the roof is now safe to access\n";
                        }
                        
                        else 
                        {
                            message = " cannot use this here\n";
                        }
                        break;

                    case "note":
                        message = " there is something written\n";
                        break;

                    case "medkit":
                        // player heal method
                        message = " health restored " + whatItem.value + "%\n";

                        zuul.player.healPlayer(whatItem.value);

                        // remove from inventory
                        zuul.player.inventory.remove(index);

                        break;

                    default:
                        message = "";
                        break;
                  }
            }

            else if (zuul.MAP.getCurrentLocation().getShortDescription().equals("at the roof")
             && item.equals("ladder"))
            {
                //change location
                zuul.MAP.teleport("alley");

                // if no gas mask, player will fall down ladder
                if (zuul.player.gasMask == false) {

                    //reduce player health due to fall damage
                    zuul.player.inflictDamage(20);

                    zuul.alert += "you fell down the ladder.";
                }
            }

            // if player is in the alley
            else if (zuul.MAP.getCurrentLocation().getShortDescription().equals("in the alley")
             && item.equals("ladder"))
            {
                //change location
                zuul.MAP.teleport("roof");
            }

            // if player is in the waiting room
            else if (zuul.MAP.getCurrentLocation().getShortDescription().equals("in the waiting room")
             && item.equals("keypad"))
            {
                slow.print("\n enter code :", 50);
                if (System.console().readLine().equals("1445")) {

                    System.out.print(ANSI_GREEN);
                    slow.print("\n code accepted. door opened\n", 50);
                    System.out.print(ANSI_RESET);

                    // set room status to open
                    zuul.MAP.setStatus("the Street", "open");
                }

                else
                {
                    System.out.print(ANSI_RED);
                    slow.print("\n incorrect\n", 50);
                    System.out.print(ANSI_RESET);
                }
            }

            else
            {
                message = " you do not have this item\n";
            }
        }
        // set last line in game class
        zuul.lastLine = message;
    }
}