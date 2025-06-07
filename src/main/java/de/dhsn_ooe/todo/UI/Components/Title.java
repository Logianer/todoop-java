package de.dhsn_ooe.todo.UI.Components;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import de.dhsn_ooe.todo.UI.Helpers.ThemeManager;

/**
 * A Title is a centered label that is bold and underlined.
 * It is supposed to be the Heading of the current frame.
 * 
 * A Title has the function of conveying the meaning
 * of the current window in less than 70 characters.
 */
public class Title extends JLabel {

    /**
     * constructs the title
     * @param text title
     * @param fontSize Size of the title
     */
    public Title(String text, int fontSize) {
        super();
        Font titleFont = ThemeManager.modifyDefaultFont(Map.of(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON,TextAttribute.SIZE, fontSize,TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD));
        
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setFont(titleFont);
        this.setText(text);
    }
    
    /**
     * Use a default of 24pt as the font size.
     * @param text title 
     */
    public Title(String text) {
        this(text, 24);
    }
}
