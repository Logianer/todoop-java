package de.dhsn_ooe.todo.UI.Components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import de.dhsn_ooe.todo.Model.TodoCheckList;

public class TodoCheckboxList extends JPanel{

    protected TodoCheckList checkboxes;
    protected Font font1;
    protected Font font2;
    public TodoCheckboxList(Font font1, Font font2, TodoCheckList checkboxes){
        super();
        this.checkboxes = checkboxes;
        this.font1 = font1;
        this.font2 = font2;
        int i;
        for (i=0; i<checkboxes.size(); i++) {
        JCheckBox checkbox = checkboxes.get(i);
        checkbox.addItemListener((ItemEvent e) -> {
            onItemStateChanged(e, checkbox);
        });
        this.setLayout(new GridLayout(0, 1));
        this.add(checkbox);
        }
    }

    private void onItemStateChanged(ItemEvent e, JCheckBox checkbox) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
                    checkboxes.remove(checkbox);
                    checkbox.setForeground(Color.GRAY);
                    checkbox.setFont(font1);
                    checkboxes.add(checkbox);
                    checkboxes.add(checkbox);
                    checkboxes.add(checkbox);
                } else {
                    checkbox.setForeground(Color.BLACK);
                    checkbox.setFont(font2);
                }
            }
    }
