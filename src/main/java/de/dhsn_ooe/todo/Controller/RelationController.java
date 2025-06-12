package de.dhsn_ooe.todo.Controller;

import java.sql.SQLException;
import java.util.List;

public interface RelationController<E, T> {
    public List<T> getRelatedItems(E object) throws SQLException;
}
