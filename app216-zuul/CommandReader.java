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
     */
    public boolean getCommand()
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

    private boolean executeCommand()
    {
        if(commandWord == "" || commandWord == null)
        {
            System.out.println("Type 'help' if you need help.");
        }
        else {
            if(commandWord.equals(CommandWords.GO.word))
        {
            GoCommand go = new GoCommand(game, word2);
            go.execute();
        }
        else if(commandWord.equals(CommandWords.TAKE.word))
        {
            TakeCommand take = new TakeCommand(game, word2);
            take.execute();
        }
        else if(commandWord.equals(CommandWords.DROP.word))
        {
            DropCommand drop = new DropCommand(game, word2);
            drop.execute();
        }
        else if(commandWord.equals(CommandWords.USE.word))
        {
            UseCommand use = new UseCommand(game, word2);
            use.execute();
        }
        else if(commandWord.equals(CommandWords.REMOVE.word))
        {
            RemoveCommand remove = new RemoveCommand(game, word2);
            remove.execute();
        }
        else if(commandWord.equals(CommandWords.STATUS.word))
        {
            StatusCommand status = new StatusCommand(game);
            status.execute();
        }
        else if(commandWord.equals(CommandWords.INVENTORY.word))
        {
            InventoryCommand inventory = new InventoryCommand(game);
            inventory.execute();
        }  
        else if(commandWord.equals(CommandWords.HELP.word))
        {
            HelpCommand help = new HelpCommand(game);
            help.execute();
        }
        else if(commandWord.equals(CommandWords.QUIT.word))
        {
            return true;  // game over
        }
        else if(commandWord.equals(HiddenCommandWords.HBOOST.word))
        {
            HBoostCommand hBoost = new HBoostCommand(game);
            hBoost.execute();
        }
        }
        

        // Return false means the game is not over
        return false;
    }
}
