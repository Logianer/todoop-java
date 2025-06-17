package de.dhsn_ooe.todo.UI.Components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import de.dhsn_ooe.todo.Controller.TodoListController;
import de.dhsn_ooe.todo.Events.TodoControllerListener;
import de.dhsn_ooe.todo.Controller.TodoItemController;
import de.dhsn_ooe.todo.Model.TodoCheckList;
import de.dhsn_ooe.todo.Model.TodoItem;
import de.dhsn_ooe.todo.UI.Helpers.ThemeManager;

/**
 * class that represents a list of checkboxes on the UI of the app
 * The checkboxes can be clicked an will move to the bottom of the displayed
 * list, the text will change its color and gets striked through.
 * When unchecked the checkbox will move to it's original spot on the list
 */
public class TodoCheckboxList extends JPanel {

    /**
     * list of the checkboxes
     */
    protected TodoCheckList list;
    protected TodoControllerListener<TodoItemController> listener = new TodoControllerListener<TodoItemController>() {
        @Override
        public void listChanged(TodoItemController list) {
            repaintButtons();
        }
    };

    /**
     * constructs a TodoCheckBox list and prints all the given checkboxes on the
     * panel with the desired layout
     * 
     * @param list chechboxes that should be displayed on the panel
     */
    public TodoCheckboxList(TodoCheckList list) {
        super();
        this.list = list;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        new TodoItemController().addListener(listener);
        this.paintButtons();
    }

    /**
     * adds checkboxes to the panel with the desired layout depending on the state
     * of the item
     */
    public final void paintButtons() {
        List<TodoItem> selectedItems = new ArrayList<>();
        List<TodoItem> items;
        items = new TodoListController().getRelatedItems(list);
        for (TodoItem item : items) {
            if (item.getState() == true) {
                selectedItems.add(item);
            } else {
                this.add(new SingleTodoItem(item));
            }
        }
        for (TodoItem item : selectedItems) {
            this.add(new SingleTodoItem(item));
        }
    }

    /**
     * removes all the buttons from the panel and repaints them
     */
    public void repaintButtons() {
        this.removeAll();
        this.paintButtons();
        this.revalidate();
        this.repaint();
    }

    /**
     * repaints the window if a item was changed (checkbox
     * checked/unchecked/removed/added)
     * 
     * @param e    event that occured
     * @param item item that the event occured on
     */
    private void onItemStateChanged(ItemEvent e, TodoItem item) {
        item.setState(e.getStateChange() == ItemEvent.SELECTED);
        new TodoItemController().update(item, item.getId());
        repaintButtons();
    }

    public void onBeforeDestroy() {
        new TodoItemController().removeListener(listener);
    }
}
