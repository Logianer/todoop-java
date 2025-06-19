package de.dhsn_ooe.todo;

import javax.swing.SwingUtilities;

import de.dhsn_ooe.todo.Controller.SQLiteDB;
import de.dhsn_ooe.todo.UI.MainFrame;

/**
 * The Main class
 */
public class Main {
        /**
         * This property is used by all child JFrames for having a reference Location to
         * appear at.
         */
        public static MainFrame mainFrame;

        /**
         * The starting point of the application
         * 
         * @param args ..
         */
        public static void main(String[] args) {
                SQLiteDB.init();
                SwingUtilities.invokeLater(() -> {
                        mainFrame = new MainFrame();
                });

        }
}
