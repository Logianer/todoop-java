package de.dhsn_ooe.todo.Controller;

import java.sql.SQLException;
import java.util.List;

/**
 * class that represents the relation between the objects of the todoapp
 */
public interface RelationController<E, T> {

    /**
     * get the related items for a given objects
     * @param object object that the related items should be displayed for
     * @return list of the related items
     * @throws SQLException error that occurs if database querys fail
     */
    public List<T> getRelatedItems(E object) throws SQLException;
}
