package de.dhsn_ooe.todo.Exception;

/**
 * class that represents the exception of the list not beeing found 
 */
public class TodoListNotFoundException extends RuntimeException {

    /**
     * displays an error if the list isn't found
     * @param id id of the list
     */
    public TodoListNotFoundException(Long id) {
      super("Could not find list #" + id);
    }
  }