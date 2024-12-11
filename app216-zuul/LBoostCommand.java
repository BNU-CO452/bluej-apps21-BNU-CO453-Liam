/**
 * This hidden command is a cheat
 * that allows the player to
 * teleport to the end of the game
 *
 * @author Liam Smith
 * @version 3/1/22
 * 
 */

public class LBoostCommand extends ZuulCommand
{   
    public LBoostCommand(Game zuul)
    {
        super(zuul);
    }

    public void execute()
    {
        zuul.MAP.teleport();
    }
}