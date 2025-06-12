package de.dhsn_ooe.todo.UI.Components;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import de.dhsn_ooe.todo.Model.TodoCheckList;
import de.dhsn_ooe.todo.Model.TodoItem;
import de.dhsn_ooe.todo.UI.Helpers.ThemeManager;

/**
 * class that represents a list of checkboxes on the UI of the app
 * The checkboxes can be clicked an will move to the bottom of the displayed list an the text will change its color and gets striked through
 * When unchecked the checkbox will move to the original spot of the list
 */
public class TodoCheckboxList extends JPanel {

    /**
     * list of the checkboxes
     */
    protected TodoCheckList checkboxes;

    /**
     * constructs a TodoCheckBox list and prints all the given checkboxes on the panel with the desired layout
     * @param checkboxes chechboxes that should be displayed on the panel
     */
    public TodoCheckboxList(TodoCheckList checkboxes) {
        super();
        this.checkboxes = checkboxes;
        this.setLayout(new GridLayout(0, 1));
        this.paintButtons();
    }

    /**
     * adds checkboxes to the panel with the desired layout depending on the state of the item
     */
    public final void paintButtons() {
        List<TodoItem> selectedItems = new ArrayList<>();
        for (TodoItem item : checkboxes) {
            if (item.getState() == true) {
                selectedItems.add(item);
            } else {
                JCheckBox checkbox = new JCheckBox("<html>"+item.getStringContent() + "</html>");
                checkbox.addItemListener(e -> onItemStateChanged(e, item));
                this.add(checkbox);
            }
        }
        for (TodoItem item : selectedItems) {
            JCheckBox checkbox = new JCheckBox("<html><s>"+item.getStringContent() + "</s></html>");
            checkbox.setSelected(true);
            checkbox.setForeground(ThemeManager.getDefaults().getColor("textInactiveText"));
            checkbox.setPreferredSize(new Dimension(200, 100));

            checkbox.addItemListener(e -> onItemStateChanged(e, item));
            this.add(checkbox);
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
     * repaints the window if a item was changed (checkbox checked/unchecked/removed/added)
     * @param e event that occured
     * @param item item that the event occured on
     */
    private void onItemStateChanged(ItemEvent e, TodoItem item) {
        item.setState(e.getStateChange() == ItemEvent.SELECTED);
        repaintButtons();
    }
}
