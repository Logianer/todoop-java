package de.dhsn_ooe.todo.Controller;

import java.sql.SQLException;
import java.util.List;

import de.dhsn_ooe.todo.Exception.ItemNotFoundException;

public interface CRUDController<E> {
    public int create(E object) throws SQLException;
    public E getById(int id) throws ItemNotFoundException;
    public List<E> getAll() throws SQLException;
    public void update(E object, int id) throws SQLException;
    public boolean delete(E object) throws SQLException;    
}
