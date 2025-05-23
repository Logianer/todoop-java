package de.dhsn_ooe.todo.Exception;

public class TodoItemNotFoundException extends Exception{
    public TodoItemNotFoundException(Long id) {
        super("Could not find item #" + id);
      }
}
