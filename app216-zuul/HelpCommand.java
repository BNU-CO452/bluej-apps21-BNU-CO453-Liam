/**
 * This class displays a message, a list of 
 * available command words to the player,
 * and the players current location
 *
 * @author Liam Smith
 * @version 28/12/21
 */
public class HelpCommand extends ZuulCommand
{
    public HelpCommand(Game zuul)
    {
        super(zuul);
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and
     * a list of the command words.
     */
    public void execute()
    {
        Location currentLocation = zuul.MAP.getCurrentLocation();

        // set current location
        zuul.locationNow = currentLocation;

        System.out.println(" You are lost. You are alone. There is poisonous gas in the air " +
        "You are dying.");      
        System.out.println();
        System.out.println(" Your command words are:");
        System.out.println();
        
        for(CommandWords command : CommandWords.values())
        {
            System.out.println(" " + command.word + 
                               "\t  : " + command.description);                        
        }   
        System.out.println();
        System.out.println(" e.g. go west, take key");
        System.out.println();
        System.out.println(zuul.MAP.getCurrentLocation().getLongDescription());
    }
}
