import java.util.ArrayList;

/**
 * Creates a player with attributes
 */

public class Player
{
    public ArrayList<Item> inventory;
    int score;
    int health;
    int armour;

    // Constructor
    public Player()
    {
        this.inventory = new ArrayList<Item>();
        this.score = 0;
        this.health = 100;
        this.armour = 0;
    }

    // Show player status
    public void status()
    {
        System.out.println(" Health: " + health + " | Armour: " + armour);
    }

    // Inflict damage to player
    public void inflictDamage(int damage)
    {
        this.health -= damage;
    }
}