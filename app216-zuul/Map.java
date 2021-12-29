
/**
 * This class is reponsible for creating and
 * linking all the Locations in the game to
 * form a 2D or 3D network
 *
 *     [Janitor Cupboard]<---->[Stairs]<---->[Ladder]<---->[Alley]
 *                                 |
 *       [Store Room]<---->[Hospital Ward]<---->[Operating Theatre]<---->[Laboratory]
 *                                 |
 *  [Reception Office]<---->[Waiting Room]<---->[Street]
 *             
 * @author Derek Peacock and Nicholas Day
 * @version 2021-08-22
 * 
 * Modified and extended by Liam Smith 27/12/21
 */
public class Map
{
    // Need to add a list of exits
    
    private Location ward, theatre, storeRoom, lab, waitingRoom, reception, 
    street, stairs, cupboard, roof, ladder, alley;

    private Location currentLocation;

    /**
     * Constructor for objects of class Map
     */
    public Map()
    {
        createLocations();
    }

    /**
     * Create all the Locations and link their exits together.
     * Set the current location to the outside.
     * Both locations need to have been created before
     * their exists are linked.
     */
    private void createLocations()
    {
        createWard();
        createStoreRoom();
        createTheatre();
        createLab();
        createWaitingRoom();
        createReception();
        createStreet();
        createStairs();
        createJanitorCupboard();
        createRoof();
        createLadder();
        createAlley();

        currentLocation = ward;  // start game in hospital ward
    }
    
    /**
     * Create the hospital ward and link it to the
     * store room, waiting room and operating theatre
     */
    private void createWard()
    {
        ward = new Location("in the hospital ward");

        // set gasmask
        ward.setItem(new Item("gasmask"));
    }

    /**
     * Create the store room and link it to the hospital ward
     */
    private void createStoreRoom()
    {
        storeRoom = new Location("in the store room");

        storeRoom.setExit("east", ward);
        ward.setExit("west", storeRoom);

        // set room item(s)
        storeRoom.setItem(new Item("torch"));
    }

    /**
     * Create the operating theatre linked to the ward and the lab
     */
    private void createTheatre()
    {
        theatre = new Location("in the operating theater");
        
        theatre.setExit("west", ward);
        ward.setExit("east", theatre);

        // set room item(s)
        theatre.setItem(new Item("medkit", 50));
    }

    /**
     * Create the lab and link to the operating theatre
     */
    private void createLab()
    {
        lab = new Location("in the laboratory");

        lab.setExit("west", theatre);
        theatre.setExit("east", lab);

        // set gasmask
        lab.setItem(new Item("gasmask"));
    }

    /**
     * Create the waiting room and link it to the ward
     */
    private void createWaitingRoom()
    {
        waitingRoom = new Location("in the waiting room");
        
        waitingRoom.setExit("north", ward);
        ward.setExit("south", waitingRoom);
    }

    /**
     * Create the reception and link to the waiting room
     */
    private void createReception()
    {
        reception = new Location("in the reception office");

        waitingRoom.setExit("west", reception);
        reception.setExit("east", waitingRoom);

        // set room item(s)
        reception.setItem(new Item("note"));
    }

    /**
     * Create street and link to the waiting room
     */
    private void createStreet()
    {
        street = new Location(" in the Street");

        waitingRoom.setExit("east", street);
        street.setExit("west", waitingRoom);
    }

    /**
     * Create the stairs and link to the ward
     */
    private void createStairs()
    {
        stairs = new Location("at the stairs");

        ward.setExit("north", stairs);
        stairs.setExit("south", ward);
    }

    /**
     * Create the janitor cupboard and link to the stairs
     */
    private void createJanitorCupboard()
    {
        cupboard = new Location("at the janitor cupboard");

        cupboard.setExit("east", stairs);
        stairs.setExit("west", cupboard);

        // set room item(s)
        cupboard.setItem(new Item("fire extinguisher"));
    }

    /**
     * Create the roof and link to the ladder
     */
    private void createRoof()
    {
        roof = new Location("at the roof");

        roof.setExit("west", stairs);
        stairs.setExit("east", roof);

    }

    /**
     * Create the ladder and link to the roof
     */
    private void createLadder()
    {
        ladder = new Location("at the ladder");

        ladder.setExit("west", roof);
        roof.setExit("east", ladder);     
    }

    /**
     * Create the alley and link to the ladder
     */
    private void createAlley()
    {
        alley = new Location("in the alley");

        ladder.setExit("east", alley);
        alley.setExit("west", ladder);
    }

    /**
     * Get the current location
     */
    public Location getCurrentLocation()
    {
        return currentLocation;
    }
    
    /**
     * Enter next location
     * @param nextLocation entered location
     */
    public void enterLocation(Location nextLocation)
    {
        currentLocation = nextLocation;
    }

}
