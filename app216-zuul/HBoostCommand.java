/**
 * This hidden command is a cheat
 * that allows the player to
 * set their health to 100%
 *
 * @author Liam Smith
 * @version 30/12/21
 * 
 */

public class HBoostCommand extends ZuulCommand
{   
    public HBoostCommand(Game zuul)
    {
        super(zuul);
    }

    // execute cheat
    public void execute()
    {
        zuul.player.health = 100;
        zuul.player.i = 0;
        System.out.println("cheat activated: health boost to 100%\n");
    }
}
