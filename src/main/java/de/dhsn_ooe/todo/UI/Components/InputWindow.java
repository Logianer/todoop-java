package de.dhsn_ooe.todo.UI.Components;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import de.dhsn_ooe.todo.Main;

//title, text, backbutton
/**
 * class that represents a general input window
 */
public class InputWindow extends JFrame {

    /**
     * layout of the input window
     */
    protected BorderLayout layout = new BorderLayout();

    /**
     * container for the components of the input window
     */
    protected final Container cp = this.getContentPane();

    /**
     * message that is displayed in the window
     */
    protected String message;

    /**
     * input component of the window (e.g. textfield)
     */
    protected JComponent input;

    /**
     * action that will be performed
     */
    protected String action;

    /**
     * button that is part of the input window 
     */
    protected JButton actionButton;

    /**
     * listeners for the input window that act on certain changes
     */
    private List<ActionListener> listeners = new ArrayList<>();

    /**
     * listerner for the keys that acts if a key has been pressed
     */
    private final KeyAdapter keyListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            onPressed(e);
        }
    };

    /**
     * constructs an input window with a title, a message inside the window and the component for the input of the user
     * @param title title that is displayed as window heading
     * @param message message that will be displayed in the window
     * @param input component to put the user input into
     * @param action action that will be performed (e.g. edit...)
     */
    public InputWindow(String title, String message, JComponent input, String action) {
        this(title, message, action);
        setInputBar(input);
    }

    /**
     * constructs an input window with a title and a message inside that has the desired layout
     * closes if the close is pressed
     * adds a button and adds a listener to it that closes the window if the button is pressed
     * adds a keylisterner that can close the window if "esc" is pressed
     * @param title title that is displayed as window heading
     * @param message message that will be displayed in the window
     * @param action action that will be performed (e.g. edit...)
     */
    public InputWindow(String title, String message, String action) {
        super();
        this.action = action;
        this.message = message;
        this.setTitle(title);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 0.1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 10, 10);
        cp.add(createTopBar(), c);

        c = new GridBagConstraints();
        c.gridy = 2;
        c.weighty = 1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.SOUTHEAST;
        c.insets = new Insets(10, 10, 15, 15);
        actionButton = new JButton(this.action);
        cp.add(actionButton, c);
        actionButton.addKeyListener(keyListener);
        actionButton.addActionListener(l -> fireActionEvent(l));
        this.setSize(250, 180);
        this.setMinimumSize(new Dimension(200, 200));
        this.setLocationRelativeTo(Main.mainFrame);
        this.setVisible(true);
    }

    /**
     * creates a top bar for the window that shows the desired message in the window
     * the text is aligned with the left side of the frame
     * @return label that the title is put on
     */
    private JComponent createTopBar() {
        JLabel title = new JLabel(this.message, SwingConstants.LEFT);
        return title;
    }

    /**
     * creates the inputbar for the window
     * adds a keylisterner to the input component of the window 
     * adds the input bar to the component with the desired layout
     * @param input inputbar
     */
    public final void setInputBar(JComponent input) {
        if (this.input != null) {
            return;
        }
        this.input = input;
        this.input.addKeyListener(keyListener);
        cp.add(this.input, this.getInputConstraints());
    }

    /**
     * closes the window if "esc" is pressed
     * @param e key that has been pressed
     */
    private void onPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.dispose();
        }
    }

    /**
     * gets the constrains for the gridbaglayout for the components of the input window 
     * @return constrains of the layout of the input window
     */
    protected GridBagConstraints getInputConstraints() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.5;
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.BOTH;
        return c;
    }

    /**
     * adds listeners to the window
     * @param l listener for the event
     */
    public void addActionListener(ActionListener l) {
        listeners.add(l);
    }

    /**
     * removes the listeners from the window
     * @param l listener for the event
     */
    public void removeActionListener(ActionListener l) {
        listeners.remove(l);
    }

    /**
     * notifys listeners if certain actions took place
     * only enables the button to close the window if the user put something into the input component
     * @param e event that took place
     */
    protected void fireActionEvent(ActionEvent e) {
        actionButton.setEnabled(false);
        for (ActionListener listener : listeners) {
            listener.actionPerformed(e);

        }
        actionButton.setEnabled(true);
    }

    /**
     * enables the action button of the window 
     * only changes the state of the button if the button isn't currently in the given state
     * @param state state that the action button should be in
     */
    protected void setButtonEnabled(boolean state) {
        if (actionButton.isEnabled() == state)
            return;
        actionButton.setEnabled(state);
    }
}
