package de.dhsn_ooe.todo.Exception;

public class ItemNotFoundException extends Exception{
    public ItemNotFoundException(int id) {
        super("Item with the id \""+id+"\" was not found.");
    }
}
