package de.dhsn_ooe.todo.UI.Components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.dhsn_ooe.todo.Model.TodoCheckList;
import de.dhsn_ooe.todo.Model.TodoItem;
import de.dhsn_ooe.todo.UI.Helpers.FontManager;

public class TodoCheckboxList extends JPanel {

    protected TodoCheckList checkboxes;

    public TodoCheckboxList(TodoCheckList checkboxes) {
        super();
        this.checkboxes = checkboxes;
        this.setLayout(new GridLayout(0,1));
        paintButtons();
    }

    public void paintButtons() {
        Font doneFontStyle = FontManager.modifyDefaultFont(Map.of(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON));
        List<TodoItem> selectedItems = new ArrayList<>();
        for (TodoItem item : checkboxes) {
            if (item.getState() == true) {
                selectedItems.add(item);
            } else {
                JCheckBox checkbox = new JCheckBox(item.getAutowrappedString(60));
                checkbox.addItemListener(e -> onItemStateChanged(e, item));
                this.add(checkbox);
            }
        }
        for (TodoItem item : selectedItems) {
            JCheckBox checkbox = new JCheckBox(item.getAutowrappedString(60));
            checkbox.setSelected(true);
            checkbox.setForeground(FontManager.getDefaults().getColor("textInactiveText"));
            checkbox.setFont(doneFontStyle);
            checkbox.setPreferredSize(new Dimension(200, 100));

            checkbox.addItemListener(e -> onItemStateChanged(e, item));
            this.add(checkbox);
        }
    }

    public void repaintButtons() {
        this.removeAll();
        this.paintButtons();
        this.revalidate();
        this.repaint();
    }

    private void onItemStateChanged(ItemEvent e, TodoItem item) {
        item.setState(e.getStateChange() == ItemEvent.SELECTED);
        repaintButtons();
    }
}
