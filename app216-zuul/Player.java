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
    boolean isDead;

    // Constructor
    public Player()
    {
        this.inventory = new ArrayList<Item>();
        this.score = 0;
        this.health = 100;
        this.armour = 0;
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

                if (checkGasMask() == false && health >= 10) {
                    inflictDamage(10);

                    if (health == 50) {
                        System.out.println(" Your health is at 50%. You are coughing blood...");
                        System.out.print(" > ");              
                    }
                }

                else if (checkGasMask() == true)
                {
                    System.out.println("Now you can breathe.");
                    timer.cancel();
                    System.out.print(" > ");  
                }
                
                else
                {
                    System.out.println("\n You are dead. Type \"quit\" and press Enter to end game.");
                    System.out.print(" > ");  
                    
                    isDead = true;
                    timer.cancel();
                    // add lose game here

                }
            } 
        };

        if(checkGasMask() == false && health >= 10)
        {
            timer.scheduleAtFixedRate(task, 0, 2000);
        }
        return isDead;
    }
}