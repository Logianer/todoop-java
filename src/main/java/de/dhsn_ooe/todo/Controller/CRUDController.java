package de.dhsn_ooe.todo.Controller;

import java.util.List;

import de.dhsn_ooe.todo.Exception.ItemNotFoundException;
import de.dhsn_ooe.todo.Exception.OrphanedRelationException;

/**
 * controller for the actions delete, update, create and read (getbyid and getall)
 * @param <E> The Class to apply the CRUD principle to. In the MVC-architecture, E will almost always me a Model.
 */
public interface CRUDController<E> {

    /**
     * creates an object
     * @param object object that will be created
     * @return a positive integer representing the unique id of the created record in the databse for later reference. If zero is returned, the object was not created.
     */
    public int create(E object);

    /**
     * gets an object of the list by it's id
     * @param id id of the item
     * @return an exception
     * @throws ItemNotFoundException exception that occurs if the item wasn't found
     * @throws OrphanedRelationException exception that occurs if the item has no connection to a list
     */
    public E getById(int id) throws ItemNotFoundException, OrphanedRelationException;

    /**
     * gets all items of the list
     * @return exceptions for the items
     */
    public List<E> getAll();

    /**
     * updates the given object
     * @param object object that will be updated
     * @param id id of the object
     * @return returns if the object was updated successfully
     */
    public boolean update(E object, int id);

    /**
     * deletes the given object
     * @param object object that will be deleted
     * @return returns if the object was deleted successfully
     */
    public boolean delete(E object);    
}
