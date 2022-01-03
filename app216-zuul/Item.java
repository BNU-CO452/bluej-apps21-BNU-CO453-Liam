
/**
 * This class creates items
 */

public class Item {
    String description;
    int value;

    // Constructor
    public Item(String newDescription) {
        this.description = newDescription;
    }

    // Constructor for item with int value
    public Item(String newDescription, int value)
    {
        this.description = newDescription;
        this.value = value;
    }

    // Returns description of item
    public String getDescription() {
        return description;
    }
}
