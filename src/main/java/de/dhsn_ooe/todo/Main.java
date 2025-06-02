package de.dhsn_ooe.todo;

import java.awt.Font;
import java.awt.font.TextAttribute;

import javax.swing.JFrame;

import de.dhsn_ooe.todo.Helpers.Fonts.fontEditor;
import de.dhsn_ooe.todo.Model.TodoCheckList;
import de.dhsn_ooe.todo.Model.TodoItem;
import de.dhsn_ooe.todo.UI.MainFrame;
import de.dhsn_ooe.todo.UI.Components.TodoCheckboxList;

/**
 * The Main class
 */
public class Main {
    /**
     * The starting point of the application
     */
    public static void main(String[] args) {
        new MainFrame();

//        if (false) {
        //create JFrame
        JFrame frame = new JFrame("Checkbox List Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);


        //create Fonts
        fontEditor fonts = new fontEditor();
        fonts.getDefaultCheckboxFont();
        Font font1 = fonts.changeDefaultCheckboxFont(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
        Font font2 = fonts.changeDefaultCheckboxFont(TextAttribute.STRIKETHROUGH, null);

        //create list of checkboxes
        TodoCheckList tasks = new TodoCheckList();
        TodoItem task1 = new TodoItem();
        TodoItem task2 = new TodoItem();
        task1.setStringContent("Aufgabe 1");
        task2.setStringContent("Aufgabe 2");
        tasks.add(task1);
        tasks.add(task2);
        TodoCheckboxList check = new TodoCheckboxList(font1, font2, tasks);


        //add checkboxes to list 
//        check.addCheckbox("Das ist ein Test", font1, font2, panel, check);
//        check.addCheckbox("Das ist ein 2. Test", font1, font2, panel, check);
//        check.addCheckbox("Das ist ein letzter Test", font1, font2, panel, check);

        //print list
//        check.printCheckboxes(panel);

        //make the panel visible
        frame.add(check);
        frame.setVisible(true);
//        };
    }
}
