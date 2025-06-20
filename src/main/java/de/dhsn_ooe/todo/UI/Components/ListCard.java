package de.dhsn_ooe.todo.UI.Components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.kordamp.ikonli.materialdesign2.MaterialDesignD;
import org.kordamp.ikonli.materialdesign2.MaterialDesignF;
import org.kordamp.ikonli.materialdesign2.MaterialDesignO;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.kordamp.ikonli.swing.FontIcon;

import de.dhsn_ooe.todo.Controller.TodoListController;
import de.dhsn_ooe.todo.Controller.TodoNoteController;
import de.dhsn_ooe.todo.Events.WindowManager;
import de.dhsn_ooe.todo.Model.AbstractTodoList;
import de.dhsn_ooe.todo.Model.TodoCheckList;
import de.dhsn_ooe.todo.Model.TodoItem;
import de.dhsn_ooe.todo.Model.TodoNote;
import de.dhsn_ooe.todo.UI.Views.SingleTodoList;
import de.dhsn_ooe.todo.UI.Views.SingleTodoNote;

/**
 * class that repesents a card of a list at the home menu of the app
 */
public class ListCard extends JPanel {

    /**
     * title of the card
     */
    private AbstractTodoList list;

    /**
     * constructs a card with its layout, title and preview for the checklis or note
     * 
     * @param list list that is displayed on the card
     */
    public ListCard(AbstractTodoList list) {
        super();
        this.list = list;
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(200, 150));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridy = 0;

        this.setBorder(BorderFactory.createLineBorder(UIManager.getColor("textInactiveText"), 1));
        this.add(createActionBar(), c);
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1.0;
        c.weighty = 1.0;
        switch (list) {
            case TodoCheckList l:
                this.add(createListPreview(), c);
                break;
            case TodoNote n:
                this.add(createNotePreview(), c);
            default:
                break;
        }

    }

    /**
     * creates the top bar of a card with title, back button, edit button, delete
     * button and the right layout
     * creates a text for hovering over the buttons
     * adds actionlisteners to the buttons that act on certain changes
     * (e.g. window changes to the desired todolist or todonote if the open button
     * is pressed)
     * adds all the constructed elements to a panel
     * @return panel with all elements
     */
    private JPanel createActionBar() {
        JPanel bar = new JPanel();
        bar.setLayout(new GridBagLayout());
        GridBagConstraints labelC = new GridBagConstraints();
        labelC.anchor = GridBagConstraints.NORTHWEST;
        labelC.gridy = 0;
        labelC.gridx = 0;
        labelC.weightx = 1.0;
        JLabel listName = new JLabel(list.getTitle());

        // set Icon depending on List Type
        if (list.getType() == TodoCheckList.TYPE) {
            listName.setIcon(FontIcon.of(MaterialDesignF.FORMAT_LIST_CHECKS, 24,
                    UIManager.getColor("textInactiveText")));
        } else if (list.getType() == TodoNote.TYPE) {
            listName.setIcon(FontIcon.of(MaterialDesignF.FILE_DOCUMENT_OUTLINE, 24,
                    UIManager.getColor("textInactiveText")));
        }
        GridBagConstraints btnC = new GridBagConstraints();
        btnC.fill = GridBagConstraints.HORIZONTAL;
        btnC.anchor = GridBagConstraints.NORTHEAST;
        btnC.insets = new Insets(0, 4, 0, 2);
        btnC.gridy = 0;
        JButton deleteButton = new JButton();
        deleteButton.setIcon(
                FontIcon.of(MaterialDesignD.DELETE, 16, UIManager.getColor("Label.foreground")));
        deleteButton.setToolTipText("Liste löschen");
        JButton editButton = new JButton();
        editButton.setIcon(
                FontIcon.of(MaterialDesignP.PENCIL, 16, UIManager.getColor("Label.foreground")));
        editButton.setToolTipText("Titel bearbeiten");

        JButton openButton = new JButton();
        openButton.setIcon(
                FontIcon.of(MaterialDesignO.OPEN_IN_NEW, 16, UIManager.getColor("Label.foreground")));
        openButton.setToolTipText("Liste öffnen");

        bar.add(listName, labelC);

        bar.add(openButton, btnC);
        bar.add(editButton, btnC);
        bar.add(deleteButton, btnC);

        openButton.addActionListener(e -> {
            if (list.getType() == TodoCheckList.TYPE) {
                WindowManager.changeWindow(new SingleTodoList((TodoCheckList) list),
                        "Todo-App | \"" + list.getTitle() + "\"");
            } else if (list.getType() == TodoNote.TYPE) {
                WindowManager.changeWindow(new SingleTodoNote((TodoNote) list),
                        "Todo-App | \"" + list.getTitle() + "\"");
            }
        });
        editButton.addActionListener(e -> {
            TitleInputWindow window = new TitleInputWindow("Neuen Titel für \"" + list.getTitle() + "\" eingeben",
                    "Bearbeiten", list.getTitle());
            window.addActionListener(l -> {
                list.setTitle(window.getTextContent());
                if (list.getType() == TodoCheckList.TYPE) {
                    new TodoListController().update((TodoCheckList) list, list.getId());
                } else if (list.getType() == TodoNote.TYPE) {
                    new TodoNoteController().update((TodoNote) list, list.getId());
                }
                window.dispose();
            });
        });
        deleteButton.addActionListener(e -> {
            if (list.getType() == TodoCheckList.TYPE) {
                new TodoListController().delete((TodoCheckList) list);
            } else if (list.getType() == TodoNote.TYPE) {
                new TodoNoteController().delete((TodoNote) list);
            }
        });
        return bar;
    }

    /**
     * creates a preview for a checklist
     * uses a stringbuilder construct the elements of the preview 
     * (content of item plus symbol (checked/unchecked))
     * displays the first five items of the list and shows if it is checked or not
     * if the list doesn't have five items the number of items on the list will be displayed
     * if the list has more then five element the additional number of items will be displayed
     * @return preview of the list
     */
    private JComponent createListPreview() {
        JLabel preview = new JLabel();
        List<TodoItem> items = new TodoListController().getRelatedItems((TodoCheckList) list);
        Collections.sort(items);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Integer.min(5, items.size()); i++) {
            TodoItem item = items.get(i);
            String todoState = item.getState() ? "◉" : "◎";
            sb.append(todoState + " " + item.getStringContent() + "<br>");
        }
        if (items.isEmpty()) {
            sb.append("<i>Leere Liste</i>");
        } else if (items.size() > 5) {
            sb.append("<i>+" + (items.size() - 5) + " mehr</i>");
        }
        preview.setText("<html>" + sb.toString() + "</html>");
        return preview;
    }

    /**
     * creates a preview for a note
     * show that the note has no content if it's empty
     * if there is content it will be shown as preview
     * if the content is longer than 100 characters only the first 100 are shown
     * @return preview of the note
     */
    private JComponent createNotePreview() {
        JLabel preview = new JLabel();
        String content = ((TodoNote) list).getContent();
        if (content == null || content.isBlank()) {
            preview.setText("<html><i>Leere Notiz</i></html>");
        } else if (content.length() < 100) {
            preview.setText(content);
        } else {
            preview.setText(content.substring(0, 100) + "...");
        }
        return preview;
    }
}
