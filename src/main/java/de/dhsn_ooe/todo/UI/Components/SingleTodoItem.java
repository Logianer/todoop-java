package de.dhsn_ooe.todo.UI.Components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.kordamp.ikonli.materialdesign2.MaterialDesignD;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.kordamp.ikonli.swing.FontIcon;

import de.dhsn_ooe.todo.Controller.TodoItemController;
import de.dhsn_ooe.todo.Model.TodoItem;

public class SingleTodoItem extends JPanel {

    private final TodoItem item;
    protected JCheckBox checkbox = new JCheckBox();

    public SingleTodoItem(TodoItem item) {
        super();
        this.item = item;
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(100, 50));
        
        JButton deleteButton = new JButton();
        deleteButton.setIcon(FontIcon.of(MaterialDesignD.DELETE, 16, UIManager.getColor("Label.foreground")));
        deleteButton.setToolTipText("Item löschen");

        JButton editButton = new JButton();
        editButton.setIcon(FontIcon.of(MaterialDesignP.PENCIL,16,UIManager.getColor("Label.foreground")));
        editButton.setToolTipText("Item bearbeiten");


        if (item.getState()) {
            checkbox.setText("<html><s>" + item.getStringContent() + "</s></html>");
            checkbox.setSelected(true);
            editButton.setIcon(FontIcon.of(MaterialDesignP.PENCIL, 16, UIManager.getColor("textInactiveText")));
            editButton.setEnabled(false);
            editButton.setToolTipText("Item bearbeiten (gesperrt)");
        } else {
            checkbox.setText("<html>" + item.getStringContent() + "</html>");
        }

        checkbox.addItemListener(e -> onItemStateChanged(e));
        editButton.addActionListener(e -> onEditButtonClick());
        deleteButton.addActionListener(e -> onDeleteButtonClick());
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.gridx = 0;
        this.add(checkbox, c);

        c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 1;
        this.add(editButton, c);

        c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 2;
        this.add(deleteButton, c);
    }

    private void onItemStateChanged(ItemEvent e) {
        item.setState(e.getStateChange() == ItemEvent.SELECTED);
        new TodoItemController().update(item, item.getId());
    }

    private void onDeleteButtonClick() {
        new TodoItemController().delete(item);
    }

    private void onEditButtonClick() {
        TodoInputWindow window = new TodoInputWindow("Bearbeiten", item.getStringContent());

        window.addActionListener(l -> {
            item.setStringContent(window.getTextContent());
            new TodoItemController().update(item, item.getId());
            window.dispose();
        });  
    }

}
