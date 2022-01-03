import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class Location - a location on the map of an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Location" represents one place in the scenery of the game.  It is 
 * connected to other locations via exits.  For each existing exit, the 
 * location stores a reference to the neighboring locations.
 * 
 * @author  Michael Kölling and David J. Barnes
 * 
 * Modified and extended by Liam Smith 27/12/21
 */

public class Location 
{
    private String description;
    private String status;
    private HashMap<String, Location> exits;        // stores exits of this room.
    public ArrayList<Item> items;

    /**
     * Create a location described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     */
    public Location(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<Item>();
    }

    /**
     * create a location with a specific status
     * @param description
     * @param status could be locked etc.
     */
    public Location(String description, String status) 
    {
        this.description = description;
        this.status = status;
        exits = new HashMap<>();
        items = new ArrayList<Item>();
    }

    /**
     * get room status
     */
    public String getRoomStatus()
    {
        return status;
    }

    /**
     * set room status
     */
    public String setRoomStatus(String newStatus)
    {
        this.status = newStatus;
        return status;
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Location neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n\n" + getExitString();
    }

    /**
     * Return a string describing the locations's exits, 
     * for example "Exits: north west".
     */
    public String getExitString()
    {
        String returnString = " Exits:";
        Set<String> keys = exits.keySet();
        
        for(String exit : keys) 
        {
            returnString += " " + exit;
        }

        returnString += "\n\n Items in the area:\n ";

        // checks if room is dark
        if (getRoomStatus().equals("dark"))
        {
            returnString += " too dark to see anything";
        }
        
        else
        {
            returnString += getRoomItems();
        }
        
        returnString += "\n";

        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Location getExit(String direction) 
    {
        return exits.get(direction);
    }

    /**
     * Get an item from the room by index.
     */
    public Item getItem(int index)
    {
        return items.get(index);
    }

    /**
     * Get an item from the room by string.
     */
    public Item getItem(String itemName)
    {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getDescription().equals(itemName)) {
                return items.get(i);
            }
        }
        return null;
    }

    /**
     * Remove an item from the room.
     */
    public void removeItem(String itemName)
    {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getDescription().equals(itemName)) {
                items.remove(i);
            }
        }
    }

    /**
     * Set an item in the room.
     */
    public void setItem(Item newItem)
    {
        items.add(newItem);
    }

    /**
     * Get items in the room
     */
    public String getRoomItems()
    {
        String output = "";

        if(items.size() > 0)
        {
            for(int i = 0; i < items.size(); i++)
            {
                output += items.get(i).getDescription() + " ";                   
            }
        }

        else {
            output = " no items";
        }


        
        return output;
    }

    /**
     * Hide room items
     */
    public String hideRoomItems()
    {
        String output = " too dark to see anything";

        return output;
    }
}

