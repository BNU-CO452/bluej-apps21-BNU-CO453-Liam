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
    String item;
    Item whatItem;
    int index;
    String message = "";
    boolean carrying = false;
    SlowString slow = new SlowString();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    
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
        Location currentLocation = zuul.MAP.getCurrentLocation();
        // set current location
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
                if (zuul.player.inventory.get(i).getDescription().equals(item))
                {
                    carrying = true;
                    whatItem = zuul.player.inventory.get(i);
                    index = i;
                }
            }

            if (carrying == true)
            {
                switch(item)
                {
                    case "torch":
                        message = " now you can see the exit";
                        break;

                    case "key":
                        if (zuul.MAP.getCurrentLocation().getShortDescription().equals("the waiting room"))
                        {
                            zuul.MAP.setStatus("the reception office", "open");
                            message = " you opened the reception office";
                        }
                        else 
                        {
                            message = " cannot use this here";
                        }
                        break;

                    case "note":
                        message = " there is something written";
                        break;

                    case "medkit":
                        // player heal method
                        zuul.player.healPlayer(whatItem.value);
                        message = " you have healed " + whatItem.value + "%";

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
                //message = " you climbed the ladder";
                zuul.MAP.teleport("alley");
            }

            else if (zuul.MAP.getCurrentLocation().getShortDescription().equals("in the alley")
             && item.equals("ladder"))
            {
                //message = " you climbed the ladder";
                zuul.MAP.teleport("roof");
            }

            // keypad
            if (zuul.MAP.getCurrentLocation().getShortDescription().equals("the waiting room")
             && item.equals("keypad"))
            {
                slow.print(" enter code :", 50);
                if (System.console().readLine().equals("1445")) {
                    // could be green

                    System.out.print(ANSI_GREEN);
                    slow.print("\n code accepted. door opened", 50);
                    System.out.print(ANSI_RESET);

                    // set room status to open
                    zuul.MAP.setStatus("the Street", "open");
                }

                else
                {
                    System.out.print(ANSI_RED);
                    slow.print("\n incorrect. door locked", 50);
                    System.out.print(ANSI_RESET);
                }
            }

            else
            {
                message = " you do not have this item";
            }
        }
        //System.out.println(message);
        zuul.lastLine = message;
    }
}