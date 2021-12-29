import java.io.IOException;

public class Program
{
    private static Game game;

    public static void main(String[] args) throws IOException
    {
        game = new Game();
        game.play();
    }
}
