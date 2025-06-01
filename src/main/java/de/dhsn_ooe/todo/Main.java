package de.dhsn_ooe.todo;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.font.TextAttribute;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.dhsn_ooe.todo.Model.TodoCheckboxList;
import de.dhsn_ooe.todo.UI.MainFrame;

/**
 * The Main class
 */
public class Main {
    /**
     * The starting point of the application
     */
    public static void main(String[] args) {
        new MainFrame();

        //create JFrame
        JFrame frame = new JFrame("Checkbox List Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        //create JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        //create list of checkboxes
        TodoCheckboxList check = new TodoCheckboxList();

        //create Fonts
        Font font1 = check.createFont(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
        Font font2 = check.createFont(TextAttribute.STRIKETHROUGH, null);

        //add checkboxes to list 
        check.addCheckbox("Das ist ein Test", font1, font2, panel, check);
        check.addCheckbox("Das ist ein 2. Test", font1, font2, panel, check);
        check.addCheckbox("Das ist ein letzter Test", font1, font2, panel, check);

        //print list
        check.printCheckboxes(panel);

        //make the panel visible
        frame.add(panel);
        frame.setVisible(true);

    }
}
