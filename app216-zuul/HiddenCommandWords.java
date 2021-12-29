/**
 * This contains cheat codes and death codes etc
 *
 * @author Liam Smith
 * @version 29/12/21
 * 
 */

public enum HiddenCommandWords
{
    RESTART ("restart", "Restart the game"),
    HBOOST ("hboost", "reset health to 100%"),
    ABOOST ("aboost", "reset armour to 100%"),
    DEAD ("dead", "Player has died. End the game");
    
    public final String word;
    public final String description;
    
    HiddenCommandWords(String word, String description)
    {
        this.word = word;
        this.description = description;
    }
}