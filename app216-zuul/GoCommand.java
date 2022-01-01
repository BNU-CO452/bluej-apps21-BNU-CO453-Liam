
/**
 * This command transfers the player from
 * one location to another location provided the
 * two locations are linked by a valid exit
 *
 * @author Derek Peacock & Nicholas Day
 * @version 2021-08-23
 */
public class GoCommand extends ZuulCommand
{
    String direction;
    String message = "";
    
    public GoCommand(Game zuul, String direction)
    {
        super(zuul);
        this.direction = direction;
    }    

    public void execute()
    {
        Map map = zuul.MAP;
        
        // Try to leave current room.
        Location currentLocation = map.getCurrentLocation();

        // set current location
        zuul.locationNow = currentLocation;

        Location nextLocation = currentLocation.getExit(direction);

        if(direction == null) 
        {
            // if there is no second word, we don't know where to go...
            message = "Go where?";
            zuul.lastLine = message;
            return;
        }

        else if (nextLocation == null) 
        {
            message = "There is no exit in this direction!";
            zuul.lastLine = message;
        }

        else if (nextLocation.getRoomStatus().contains("locked"))
        {
            message += nextLocation.getShortDescription();
            message += nextLocation.getRoomStatus();
        }

        else
        {
            map.enterLocation(nextLocation);

            // this will print twice with hud
            //message = map.getCurrentLocation().getLongDescription();
        }

        // this will print twice with hud
        //System.out.println(message);

        zuul.lastLine = message;
    }
}
