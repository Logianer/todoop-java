package de.dhsn_ooe.todo.UI.Helpers;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.UIManager;

/**
 * class that manages the overall theme of the app (fonts etc.)
 */
public class ThemeManager {

    /**
     * gets the default font used on a label
     * @return default font
     */
    public static Font getDefaultFont() {
        return UIManager.getDefaults().getFont("RootPane.font");
    }

    /**
     * modifies the default font 
     * @param newAttributes Attributes that should be changed on the default font
     * @return modified font
     */
    public static Font modifyDefaultFont(Map<TextAttribute, Object> newAttributes) {
        Map<TextAttribute, Object> attributes = new HashMap<>(getDefaultFont().getAttributes());
        attributes.putAll(newAttributes);
        return getDefaultFont().deriveFont(attributes);
    }

    public static void setTransparentButton(JButton button) {
        button.setContentAreaFilled(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
