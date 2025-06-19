package de.dhsn_ooe.todo.UI.Components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import de.dhsn_ooe.todo.Controller.TodoItemController;
import de.dhsn_ooe.todo.Controller.TodoListController;
import de.dhsn_ooe.todo.Events.TodoControllerListener;
import de.dhsn_ooe.todo.Model.TodoCheckList;
import de.dhsn_ooe.todo.Model.TodoItem;

/**
 * class that represents a list of checkboxes on the UI of the app
 * The checkboxes can be clicked and will move to the bottom of the displayed
 * list, the text will change its color and gets striked through.
 * When unchecked the checkbox will move to it's original spot on the list
 */
public class TodoCheckboxList extends JPanel {

    /**
     * list of the checkboxes
     */
    protected TodoCheckList list;

    /**
     * gridbaglayout of the checkboxlist 
     */
    private final GridBagConstraints defaultConstraints = new GridBagConstraints(0, -1, 1, 1, 1.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0);

    /**
     * listener for the items(checkboxes) of the list that repaints the list, if changes took place
     */
    protected TodoControllerListener<TodoItemController> listener = (TodoItemController list1) -> {
        repaintButtons();
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
        this.setLayout(new GridBagLayout());
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
                this.add(new SingleTodoItem(item), defaultConstraints);
            }
        }
        for (TodoItem item : selectedItems) {
            this.add(new SingleTodoItem(item), defaultConstraints);
        }

        // Add an element that takes up all the remaining space.
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.weighty = 1.0;
        c.weightx = 1.0;
        this.add(new JPanel(), c);
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
     * removes the listener before the object is destroyed
     */
    public void onBeforeDestroy() {
        new TodoItemController().removeListener(listener);
    }
}
