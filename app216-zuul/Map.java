import java.util.ArrayList;

/**
 * This class is reponsible for creating and
 * linking all the Locations in the game to
 * form a 2D or 3D network
 *
 *     [Janitor Cupboard]<---->[Stairs]<---->[Roof]<---->[Alley]
 *                                 |
 *       [Store Room]<---->[Hospital Ward]<---->[Operating Theatre]<---->[Laboratory]
 *                                 |
 *  [Reception Office]<---->[Waiting Room]<---->[Street]
 *             
 * @author Derek Peacock and Nicholas Day
 * @version 2021-08-22
 * 
 * Modified and extended by Liam Smith 3/1/22
 */

public class Map
{
    private Location ward, hallway, theatre, storeRoom, lab, waitingRoom, reception, 
    street, stairs, cupboard, roof, alley;

    private Location currentLocation;
    public ArrayList<Location> locations;
    public String message = "";

    /**
     * Constructor for objects of class Map
     */
    public Map()
    {
        createLocations();
        locations = new ArrayList<Location>();

        locations.add(ward);
        locations.add(hallway);
        locations.add(theatre);
        locations.add(storeRoom);
        locations.add(lab);
        locations.add(waitingRoom);
        locations.add(reception);
        locations.add(street);
        locations.add(stairs);
        locations.add(cupboard);
        locations.add(roof);
        locations.add(alley);
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
        createHallway();
        createStoreRoom();
        createTheatre();
        createLab();
        createWaitingRoom();
        createReception();
        createStreet();
        createStairs();
        createJanitorCupboard();
        createRoof();
        createAlley();

        currentLocation = ward;     // start game in hospital ward
    }
    
    /**
     * Create the hospital ward and link it to the
     * store room, hallway, waiting room and operating theatre
     */
    private void createWard()
    {
        ward = new Location("in the hospital ward", "open");
    }

    /**
     * Create the hallway and link it to the ward and the stairs
     */
    private void createHallway()
    {
        hallway = new Location("in the hallway", "open");

        
        hallway.setExit("south", ward);
        ward.setExit("north", hallway);
    }

    /**
     * Create the store room and link it to the hospital ward
     */
    private void createStoreRoom()
    {
        storeRoom = new Location("in the store room", "open");

        // set room exit(s)
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
        theatre = new Location("in the operating theater", "open");
        
        // set room exit(s)
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
        lab = new Location("in the laboratory", "open");

        // set room exit(s)
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
        waitingRoom = new Location("in the waiting room", "open");
        
        // set room exit(s)
        waitingRoom.setExit("north", ward);
        ward.setExit("south", waitingRoom);

        // set room item(s)
        waitingRoom.setItem(new Item("keypad"));
    }

    /**
     * Create the reception and link to the waiting room
     */
    private void createReception()
    {
        reception = new Location("in the reception office", " is locked with a padlock\n");

        // set room exit(s)
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
        street = new Location("the Street", " is locked with a code\n");

        // set room exit(s)
        waitingRoom.setExit("east", street);
        street.setExit("west", waitingRoom);
    }

    /**
     * Create the stairs and link to the ward
     */
    private void createStairs()
    {
        stairs = new Location("at the stairs", "open");

        // set room exit(s)
        stairs.setExit("south", hallway);
        hallway.setExit("north", stairs);

        // set room item(s)
        stairs.setItem(new Item("map"));
    }

    /**
     * Create the janitor cupboard and link to the stairs
     */
    private void createJanitorCupboard()
    {
        cupboard = new Location("at the janitor cupboard", "open");

        // set room exit(s)
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
        roof = new Location("at the roof", " is blocked by fire\n");

        // set room exit(s)
        roof.setExit("west", stairs);
        stairs.setExit("east", roof);

        // set room item(s)
        roof.setItem(new Item("ladder"));

    }

    /**
     * Create the alley and link to the ladder
     */
    private void createAlley()
    {
        alley = new Location("in the alley", "dark");

        // set room item(s)
        alley.setItem(new Item("ladder"));
        alley.setItem(new Item("key"));
    }

    /**
     * Get the current location
     */
    public Location getCurrentLocation()
    {
        return currentLocation;
    }

    /**
     * set a specific room status
     */
    public void setStatus(String location, String status)
    {
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).getShortDescription().contains(location)) {
                locations.get(i).setRoomStatus(status);
            }
        }
    }
    
    /**
     * Enter next location
     * @param nextLocation entered location
     */
    public void enterLocation(Location nextLocation)
    {
        currentLocation = nextLocation;
    }

    /**
     * teleport to end of game
     */
    public void teleport()
    {
        enterLocation(street);
    }

    /**
     * teleport by string
     */
    public void teleport(String desiredLocation)
    {
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).getShortDescription().contains(desiredLocation)) {
                enterLocation(locations.get(i));
            }
        }
    }
}
