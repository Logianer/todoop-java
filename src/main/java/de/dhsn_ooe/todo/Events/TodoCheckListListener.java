package de.dhsn_ooe.todo.Events;

import java.util.EventListener;

import de.dhsn_ooe.todo.Model.TodoCheckList;

public interface TodoCheckListListener extends EventListener {
    void listChanged(TodoCheckList list);
}
