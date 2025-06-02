package de.dhsn_ooe.todo.UI.Components;

import java.awt.Font;
import javax.swing.JPanel;

import de.dhsn_ooe.todo.Model.TodoCheckList;

public class TodoCheckboxList extends JPanel {

    protected TodoCheckList checkboxes;
    protected Font font1;
    protected Font font2;

    public TodoCheckboxList(TodoCheckList checkboxes) {
        super();
        this.checkboxes = checkboxes;
    }
}
