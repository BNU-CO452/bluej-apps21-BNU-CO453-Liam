/**
 * add comment
 */

public class StatusCommand extends ZuulCommand
{
    public StatusCommand(Game zuul)
    {
        super(zuul);
    }

    public void execute()
    {
        zuul.player.status();
    }
}
