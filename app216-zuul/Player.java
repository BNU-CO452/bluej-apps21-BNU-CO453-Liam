import java.util.ArrayList;

/**
 * Creates a player with attributes
 */

public class Player
{
    public ArrayList<Item> inventory;
    int score;
    int health;

    public Player()
    {
        this.inventory = new ArrayList<Item>();
        this.score = 0;
        this.health = 100;
    }
}