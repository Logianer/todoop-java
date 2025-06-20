package de.dhsn_ooe.todo.Exception;

/**
 * class that represents the exception that occurs if an item has no parentlist
 */
public class OrphanedRelationException extends Exception{

    /**
     * shows a message if the exception occurs
     * @param message message that is shown
     */
    public OrphanedRelationException(String message) {
        super(message);
    }
}
