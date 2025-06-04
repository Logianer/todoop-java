package de.dhsn_ooe.todo.UI.Views;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.swing.FontIcon;

import de.dhsn_ooe.todo.Events.WindowManager;
import de.dhsn_ooe.todo.Model.TodoCheckList;
import de.dhsn_ooe.todo.Model.TodoItem;
import de.dhsn_ooe.todo.UI.Dashboard;
import de.dhsn_ooe.todo.UI.Components.Title;
import de.dhsn_ooe.todo.UI.Components.TodoCheckboxList;
import de.dhsn_ooe.todo.UI.Helpers.FontManager;

public class TodoListSingle extends JPanel {

    protected BorderLayout layout = new BorderLayout();

    public TodoListSingle() {
        super();
        this.setLayout(layout);

        TodoCheckList tasks = new TodoCheckList();
        TodoItem task1 = new TodoItem();
        TodoItem task2 = new TodoItem();
        TodoItem task3 = new TodoItem();
        TodoItem task4 = new TodoItem();
        task1.setStringContent("Aufgabe 1");
        task2.setStringContent("Aufgabe 2");
        task3.setStringContent("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
        task4.setStringContent("Aufgabe 7");
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);
        TodoCheckboxList check = new TodoCheckboxList(tasks);
        
        this.add(createTopBar(), BorderLayout.NORTH);
        this.add(check, BorderLayout.CENTER);
    }

    private JPanel createTopBar() {
        JPanel panel = new JPanel();
        JButton button = new JButton();
        Title title = new Title("Liste 1", 15);
        panel.setLayout(new BorderLayout());
        button.setIcon(FontIcon.of(MaterialDesignA.ARROW_LEFT, 16, FontManager.getDefaults().getColor("Label.foreground")));
        button.addActionListener(e -> WindowManager.changeWindow(new Dashboard()));

        panel.add(title, BorderLayout.CENTER);
        panel.add(button, BorderLayout.WEST);
        return panel;
    }
}
