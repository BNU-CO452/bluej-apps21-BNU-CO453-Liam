
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

        // set current location in game class
        zuul.locationNow = currentLocation;

        // set next location
        Location nextLocation = currentLocation.getExit(direction);

        if(direction == null) 
        {
            // if there is no second word, we don't know where to go...
            message = "Go where?\n";
            zuul.lastLine = message;
            return;
        }

        // if next location is null
        else if (nextLocation == null) 
        {
            message = "There is no exit in this direction!\n";
            zuul.lastLine = message;
        }

        else if (nextLocation.getRoomStatus().contains("locked"))
        {
            message += nextLocation.getShortDescription().substring(3);
            message += nextLocation.getRoomStatus();
        }

        else if (nextLocation.getRoomStatus().contains("fire"))
        {
            message += nextLocation.getShortDescription().substring(3);
            message += nextLocation.getRoomStatus();
        }

        else
        {
            map.enterLocation(nextLocation);
        }

        // set last line in game class
        zuul.lastLine = message;
    }
}
