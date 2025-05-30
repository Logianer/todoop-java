package de.dhsn_ooe.todo;

import com.formdev.flatlaf.FlatDarkLaf;

import de.dhsn_ooe.todo.UI.MainFrame;

/**
 * The Main class
 */
public class Main {
    /**
     * The starting point of the application
     */
    public static void main(String[] args) {
                FlatDarkLaf.setup();
                new MainFrame();
    }
}
