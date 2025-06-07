package de.dhsn_ooe.todo.Exception;

/**
 * class that represents the exception of the item not beeing found
 */
public class TodoItemNotFoundException extends Exception{

    /**
     * shows error if the item wasn't found  
     * @param id id of the item
     */
    public TodoItemNotFoundException(Long id) {
        super("Could not find item #" + id);
      }
}
