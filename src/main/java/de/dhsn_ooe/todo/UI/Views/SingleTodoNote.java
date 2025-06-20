package de.dhsn_ooe.todo.UI.Views;

import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.swing.FontIcon;

import de.dhsn_ooe.todo.Controller.TodoNoteController;
import de.dhsn_ooe.todo.Events.WindowManager;
import de.dhsn_ooe.todo.Model.TodoNote;
import de.dhsn_ooe.todo.UI.Components.Title;
import de.dhsn_ooe.todo.UI.Dashboard;
import de.dhsn_ooe.todo.UI.Helpers.ThemeManager;

/**
 * class that represents a single todonote with the right layout
 */
public class SingleTodoNote extends JPanel {
    
    /**
     * layout of the note
     */
    protected BorderLayout layout = new BorderLayout();

    /**
     * note that is displayed
     */
    private TodoNote note;

    /**
     * textfield for editing the note
     */
    private JTextArea area;

    /**
     * constructs a note with the given elements, a top bar and the layout of the panel
     * @param note note the data should be displayed from
     */
    public SingleTodoNote(TodoNote note) {
        super();
        this.setLayout(layout);
        this.note = note;
        this.area = new JTextArea(note.getContent());
        JScrollPane scrollableNote = new JScrollPane(area);
        scrollableNote.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableNote.getVerticalScrollBar().setUnitIncrement(16);
        scrollableNote.setBorder(BorderFactory.createEmptyBorder());
        this.add(createTopBar(), BorderLayout.NORTH);
        this.add(scrollableNote, BorderLayout.CENTER);
    }

    /**
     * creates the top bar of the note with title, back button, save button and the right layout
     * creates a text for hovering over the buttons
     * adds actionlisteners to the buttons that act on certain changes 
     * (e.g. window changes back to the dashboard if back button is pressed)
     * adds all the constructed elements to a panel 
     * @return panel with all elements
     */
    private JPanel createTopBar() {
        JPanel panel = new JPanel();
        JButton backButton = new JButton();
        JButton saveButton = new JButton();
        Title title = new Title(note.getTitle(), 15);
        panel.setLayout(new BorderLayout());
        backButton.setMargin(new Insets(5, 5, 5, 5));
        backButton.setToolTipText("Zurück");
        saveButton.setMargin(new Insets(5, 5, 5, 5));
        saveButton.setToolTipText("Speichern");
        backButton.setIcon(
                FontIcon.of(MaterialDesignA.ARROW_LEFT, 24, UIManager.getColor("Label.foreground")));
        ThemeManager.setTransparentButton(backButton);
        backButton.addActionListener(e -> {
            WindowManager.changeWindow(new Dashboard(), "Todo-App | Start");
        });
        saveButton.setIcon(
                FontIcon.of(MaterialDesignC.CONTENT_SAVE, 24, UIManager.getColor("Label.foreground")));
        ThemeManager.setTransparentButton(saveButton);

        saveButton.addActionListener(e -> {
            saveButton.setEnabled(false);
            saveButton.setForeground(UIManager.getColor("textInactiveText"));
            note.setContent(area.getText());
            new TodoNoteController().update(note, note.getId());
            saveButton.setEnabled(true);
            saveButton.setForeground(UIManager.getColor("Label.foreground"));

        });

        panel.add(title, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.WEST);
        panel.add(saveButton, BorderLayout.EAST);
        return panel;
    }
}
