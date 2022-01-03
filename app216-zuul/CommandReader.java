import java.util.Scanner;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two-word command. It returns the command
 * as an object of class Command.
 *
 * The reader has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 * 
 * Modified and extended by Liam Smith 1/1/22
 */

public class CommandReader 
{
    private Game game;
    private Scanner reader; // source of command input

    private String commandWord = null;
    private String word2 = null;

    /**
     * Create a parser to read from the terminal window.
     */
    public CommandReader(Game game) 
    {
        this.game = game;
        reader = new Scanner(System.in);
    }
    
    /**
     * @return The next command from the user.
     * @throws InterruptedException
     */
    public boolean getCommand() throws InterruptedException
    {
        String inputLine;  
        
        System.out.print(" > ");
        
        inputLine = reader.nextLine().toLowerCase();

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        
        if(tokenizer.hasNext()) 
        {
            commandWord = tokenizer.next();      // get first word
        
            if(commandWord == null)
                {
                commandWord = "";
                }

            if(tokenizer.hasNext()) 
            {
                word2 = tokenizer.next();      // get second word
                // note: we just ignore the rest of the input line.
            }
            else word2 = null;
        }
        tokenizer.close();
        return executeCommand();
    }

    private boolean executeCommand() throws InterruptedException
    {   
        // if player is dead and types a command
        if (game.player.isDead) {
            return true; // game over
        }

        // if user input no data
        else if(commandWord == "" || commandWord == null)
            {
                System.out.println("Type 'help' if you need help.");
            }

        // if player typed "go"
        else if(commandWord.equals(CommandWords.GO.word))
        {
            GoCommand go = new GoCommand(game, word2);
            go.execute();
        }

        // if player typed "take"
        else if(commandWord.equals(CommandWords.TAKE.word))
        {
            TakeCommand take = new TakeCommand(game, word2);
            take.execute();
        }

        // if player typed "drop"
        else if(commandWord.equals(CommandWords.DROP.word))
        {
            DropCommand drop = new DropCommand(game, word2);
            drop.execute();
        }

        // if player typed "use"
        else if(commandWord.equals(CommandWords.USE.word))
        {
            UseCommand use = new UseCommand(game, word2);
            use.execute();
        }

        // if player typed "equip"
        else if(commandWord.equals(CommandWords.EQUIP.word))
        {
            EquipCommand equip = new EquipCommand(game, word2);
            equip.execute();
        }

        // if player typed "check"
        else if(commandWord.equals(CommandWords.CHECK.word))
        {
            CheckCommand check = new CheckCommand(game, word2);
            check.execute();
        }

        // if player typed "remove"
        else if(commandWord.equals(CommandWords.REMOVE.word))
        {
            RemoveCommand remove = new RemoveCommand(game, word2);
            remove.execute();
        }

        // if player typed "status"
        else if(commandWord.equals(CommandWords.STATUS.word))
        {
            StatusCommand status = new StatusCommand(game);
            status.execute();
        }

        // if player typed "inventory"
        else if(commandWord.equals(CommandWords.INVENTORY.word))
        {
            InventoryCommand inventory = new InventoryCommand(game);
            inventory.execute();
        }
        
        // if player typed "help"
        else if(commandWord.equals(CommandWords.HELP.word))
        {
            HelpCommand help = new HelpCommand(game);
            help.execute();
        }

        // if player typed "quit"
        else if(commandWord.equals(CommandWords.QUIT.word))
        {
            return true;  // game over
        }

        // if player typed "hboost"
        else if(commandWord.equals(HiddenCommandWords.HBOOST.word))
        {
            HBoostCommand hBoost = new HBoostCommand(game);
            hBoost.execute();
        }

        // if player typed "lboost"
        else if(commandWord.equals(HiddenCommandWords.LBOOST.word))
        {
            LBoostCommand lBoost = new LBoostCommand(game);
            lBoost.execute();
        }     

        // Return false means the game is not over
        return false;
    }
}
