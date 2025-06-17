package de.dhsn_ooe.todo.Controller;

import java.sql.SQLException;
import java.util.List;

import de.dhsn_ooe.todo.Exception.ItemNotFoundException;
import de.dhsn_ooe.todo.Exception.OrphanedRelationException;

public interface CRUDController<E> {
    public int create(E object);
    public E getById(int id) throws ItemNotFoundException, OrphanedRelationException;
    public List<E> getAll();
    public boolean update(E object, int id);
    public boolean delete(E object);    
}
