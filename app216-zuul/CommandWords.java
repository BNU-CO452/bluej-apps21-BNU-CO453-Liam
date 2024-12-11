
/**
 * This contains all the possible command words
 * for the game of Zuul
 *
 * @author Derek Peacock & Nicholas Day
 * @version 25-08-2021
 * 
 * Modified and extended by Liam Smith 30/12/21
 */

public enum CommandWords
{
    GO   ("go","Exit location in <direction>"),
    TAKE ("take", "Take <item> from location"),
    DROP ("drop", "Drop <item> from inventory"),
    USE ("use", "Use <item> in inventory"),
    EQUIP ("equip", "Equip <item> in inventory"),
    CHECK ("check", "Check something"),
    REMOVE ("remove", "Un equip <item> in inventory"),
    STATUS ("status", "Show Player status"),
    INVENTORY ("inventory", "Show inventory"),
    HELP ("help", "List all available commands"),
    QUIT ("quit", "End the game");
    
    public final String word;
    public final String description;
    
    CommandWords(String word, String description)
    {
        this.word = word;
        this.description = description;
    }
}
