package de.dhsn_ooe.todo.Model;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.font.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class TodoCheckboxList {
    private ArrayList <JCheckBox> checkboxes;

    public TodoCheckboxList(){
        checkboxes = new ArrayList<>();
    }

    public Font createFont(TextAttribute attribute1, Boolean attribute2){
        Font font = new Font("helvetica", Font.PLAIN, 12);
        Map <TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
        attributes.put(attribute1, attribute2);
        Font newFont = new Font(attributes);
        return newFont;
    }

    public void addCheckbox(String text, Font font1, Font font2, JPanel panel, TodoCheckboxList list){
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    JCheckBox element = checkBox;
                    checkboxes.remove(element);
                    checkBox.setForeground(Color.GRAY);
                    checkBox.setFont(font1);
                    checkboxes.add(element);
                    printCheckboxes(panel);
                } else {
                    checkBox.setForeground(Color.BLACK);
                    checkBox.setFont(font2);

                }
            }
        });
        checkboxes.add(checkBox);
    }

    public void printCheckboxes(JPanel panel){
        int i;
        for (i=0; i<checkboxes.size(); i++){
        panel.add(checkboxes.get(i));
        }
    }
}
