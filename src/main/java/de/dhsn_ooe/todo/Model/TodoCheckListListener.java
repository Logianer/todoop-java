package de.dhsn_ooe.todo.Model;

import java.util.EventListener;

public interface TodoCheckListListener extends EventListener {
    void listChanged(TodoCheckList list);
}
