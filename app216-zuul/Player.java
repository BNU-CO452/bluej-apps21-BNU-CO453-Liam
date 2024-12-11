import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Creates a player with attributes
 * 
 * @author Liam Smith
 * @version 3/1/22
 */

public class Player
{
    public ArrayList<Item> inventory;
    public ArrayList<Item> itemsCollected;
    public ArrayList<Location> roomsReached;

    public int score;
    public int health;

    public boolean gasMask;
    public boolean isDead;
    private SlowString slow = new SlowString();
    public int i = 0;

    // Constructor
    public Player()
    {
        this.inventory = new ArrayList<Item>();
        this.itemsCollected = new ArrayList<Item>();
        this.roomsReached = new ArrayList<Location>();
        this.score = 0;
        this.health = 100;
        this.gasMask = false;
        this.isDead = false;
    }

    // Show player status
    public void status()
    {
        System.out.println(" Health: " + health + " | Score: " + score);
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
    public void healPlayer(int amount)
    {
        this.health += amount;
    }

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

    // Check if player is wearing gas mask
    public boolean checkGasMask()
    {
        return this.gasMask;
    }

    // Inflict damage to player every x seconds
    // if not wearing gas mask
    public boolean gasDamage()
    {

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {

                if (checkGasMask() == true)
                {
                    timer.cancel();
                }

                else if (checkGasMask() == false && getHealth() >= 10) {

                    // start damaging player
                    inflictDamage(10);

                    // when health is at 50% print a warning message
                    if (health == 50) {

                        System.out.println("\nYour health is at 50%. You are coughing blood...");
                        System.out.print(" > ");              
                    }
                }
                
                else
                {
                    // if players health is 0. player is dead. print message
                    if(i == 0)
                    {
                        try {
                            slow.print("You are dead. press Enter to end game.", 50);
                            i++;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } 
                    
                    isDead = true;
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 20000, 40000);

        return isDead;
    }
}