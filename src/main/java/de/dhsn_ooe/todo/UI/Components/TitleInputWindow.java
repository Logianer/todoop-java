package de.dhsn_ooe.todo.UI.Components;

import javax.swing.JTextField;

import de.dhsn_ooe.todo.Model.AbstractTodoList;

/**
 * class that represents a input window for a title
 */
public class TitleInputWindow extends InputWindow{

    public TitleInputWindow(AbstractTodoList list) {
        super("Titel bearbeiten","Neuen Titel für \"" +list.getTitle()+"\" eingeben.", new JTextField(list.getTitle()), "Ok");
    }
}
