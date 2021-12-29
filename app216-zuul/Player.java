import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Creates a player with attributes
 */

public class Player
{
    public ArrayList<Item> inventory;
    public int score;
    public int health;
    public boolean armour;
    public boolean gasMask;
    public boolean isDead;
    private SlowString slow = new SlowString();

    // Constructor
    public Player()
    {
        this.inventory = new ArrayList<Item>();
        this.score = 0;
        this.health = 100;
        this.armour = false;
        this.gasMask = false;
        this.isDead = false;
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

    // Equip gas mask on
    public boolean equipGasMask()
    {
        this.gasMask = true;

        return gasMask;
    }

    // Remove gas mask
    public boolean removeGasMask()
    {
        this.gasMask = false;
        return gasMask;
    }

    // Equip armour
    public boolean equipArmour()
    {
        this.armour = true;
        return armour;
    }

    // Remove armour
    public boolean removeArmour()
    {
        this.armour = false;
        return armour;
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
                int x = 0;
                if (checkGasMask() == true)
                {
                    x++;
                    timer.cancel();
                    System.out.println("\nNow you can breathe.");
                    System.out.println(x);
                    
                    System.out.print(" > ");  
                }

                else if (checkGasMask() == false && getHealth() >= 10) {
                    inflictDamage(10);

                    if (health == 50) {

                        System.out.println("Your health is at 50%. You are coughing blood...");
                        System.out.print("\n > ");              
                    }
                }
                
                else
                {
                    try {
                        slow.print("\n You are dead. Type \"quit\" and press Enter to end game.", 25);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.print("\n > ");  
                    
                    isDead = true;
                    timer.cancel();
                    // add lose game here

                }
            } 
        };

        timer.scheduleAtFixedRate(task, 1000, 4000);

        return isDead;
    }
}