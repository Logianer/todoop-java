package de.dhsn_ooe.todo.Exception;

public class TodoListNotFoundException extends RuntimeException {
    public TodoListNotFoundException(Long id) {
      super("Could not find list #" + id);
    }
  }