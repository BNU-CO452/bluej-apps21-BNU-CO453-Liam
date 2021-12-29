import javax.lang.model.util.ElementScanner14;

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
    
    /**
     * Use an item
     */
    public UseCommand(Game zuul, String item)
    {
        super(zuul);
        this.item = item;
    }    

    public void execute()
    {
        String message;
        boolean carrying = false;

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
                        message = " you opened the door";
                        break;

                    case "gasmask":
                        if (zuul.player.gasMask = false) {
                            zuul.player.equipGasMask();
                            message = "gas mask equipped";
                        }
                        
                        else
                        {
                            zuul.player.removeGasMask();
                            message = "gas mask removed";
                        }                        
                        break;

                    case "gun":
                        message = " you used the gun";
                        break;

                    case "note":
                        message = " the note reads: 1445";
                        break;

                    case "armour":
                        message = " you have equipped armour";

                        //increase player health
                        zuul.player.armour += 50;

                        //remove from inventory
                        zuul.player.inventory.remove(whatItem);
                        break;

                    default:
                        message = "";
                        break;
                  }
            }

            else
            {
                message = " you do not have this item";
            }
        }
        System.out.println(message);
    }
}
