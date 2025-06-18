package de.dhsn_ooe.todo.UI.Views;

import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.JPanel;

import de.dhsn_ooe.todo.Controller.TodoListController;
import de.dhsn_ooe.todo.Controller.TodoNoteController;
import de.dhsn_ooe.todo.Events.TodoControllerListener;
import de.dhsn_ooe.todo.Model.TodoCheckList;
import de.dhsn_ooe.todo.Model.TodoNote;
import de.dhsn_ooe.todo.UI.Components.ListCard;

/**
 * class that represents the list of the TodoLists in the home menu
 */
public class TodoListList extends JPanel {

    /**
     * layout of the list
     */
    protected GridLayout layout = new GridLayout(0, 3, 10, 10);
    private TodoControllerListener<TodoListController> listListener = (TodoListController list) -> {
        repaintLists();
    };

    private TodoControllerListener<TodoNoteController> noteListener = (TodoNoteController list) -> {
        repaintLists();
    };

    /**
     * contstructs an empty list with the desired layout
     * adds listeners to the components to trigger the right events on incoming
     * changes
     */
    public TodoListList() {
        super();
        this.setLayout(layout);
        new TodoListController().addListener(listListener);
        new TodoNoteController().addListener(noteListener);

        paintLists();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panelResized();
            }
        });
        this.panelResized();
    }

    private void paintLists() {
        List<TodoCheckList> lists = new TodoListController().getAll();
        List<TodoNote> notes = new TodoNoteController().getAll();
        for (TodoCheckList list : lists) {
            ListCard card = new ListCard(list);
            this.add(card);
        }
        for (TodoNote note : notes) {
            ListCard card = new ListCard(note);
            this.add(card);
        }
    }

    private void repaintLists() {
        this.removeAll();
        this.paintLists();
        this.revalidate();
        this.repaint();
    }

    /**
     * changes the size of the panel if the size of the window is changed
     */
    private void panelResized() {
        int columnCount = Math.min(4, Math.max(1, this.getWidth() / 250));
        if (columnCount != layout.getColumns()) {
            layout.setColumns(columnCount);
            this.revalidate();
            this.repaint();
        }
    }

    public void onBeforeDestroy() {
        new TodoListController().removeListener(listListener);
        new TodoNoteController().removeListener(noteListener);
    }
}
