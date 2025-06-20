package de.dhsn_ooe.todo.Exception;

/**
 * class that represents the exception that occurs if an item wasn't found for the given id
 */
public class ItemNotFoundException extends Exception{

    /**
     * displays a message if an item wasn't found by it's id
     * @param id id that was looked for
     */
    public ItemNotFoundException(int id) {
        super("Item with the id \""+id+"\" was not found.");
    }
}
