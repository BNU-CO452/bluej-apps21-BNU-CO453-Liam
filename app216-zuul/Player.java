import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Creates a player with attributes
 */

public class Player
{
    public ArrayList<Item> inventory;
    int score;
    int health;
    int armour;
    boolean gasMask;
    boolean alive;

    // Constructor
    public Player()
    {
        this.inventory = new ArrayList<Item>();
        this.score = 0;
        this.health = 100;
        this.armour = 0;
        this.gasMask = false;
        this.alive = true;
    }

    // Show player status
    public void status()
    {
        System.out.println(" Health: " + health + " | Armour: " + armour);
    }

    // get player health
    public int getHealth()
    {
        return this.health;
    }

    // Inflict damage to player
    public void inflictDamage(int damage)
    {
        this.health -= damage;
    }

    // Heal player

    // equip gas mask on
    public boolean equipGasMask()
    {
        this.gasMask = true;

        return gasMask;
    }

    // remove gas mask off
    public boolean removeGasMask()
    {
        this.gasMask = false;
        return gasMask;
    }

    // Check if player is wearing gas mask
    public boolean checkGasMask()
    {
        return this.gasMask;
    }

    // Inflict damage to player every 5 seconds
    // if not wearing gas mask
    public boolean gasDamage()
    {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {

                if (getHealth() > 0 && checkGasMask() == false) {
                    inflictDamage(10);
                    System.out.println(" You are coughing blood.");
                    System.out.println(" > ");
                }
                
                else
                {
                    System.out.println(" You are dead");
                    alive = false;
                    timer.cancel();
                    // add lose game here

                }
            } 
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
        return alive;
    }
}