/**
 * This contains cheat codes
 *
 * @author Liam Smith
 * @version 29/12/21
 * 
 */

public enum HiddenCommandWords
{
    HBOOST ("hboost", "reset health to 100%"),
    MBOOST ("mboost", "equip mask"),
    LBOOST ("lboost", "teleport"),
    ABOOST ("aboost", "equip full armour");
    
    public final String word;
    public final String description;
    
    HiddenCommandWords(String word, String description)
    {
        this.word = word;
        this.description = description;
    }
}