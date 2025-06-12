package de.dhsn_ooe.todo.UI.Helpers;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.UIDefaults;

import com.formdev.flatlaf.FlatLaf;

/**
 * class that manages the overall theme of the app (fonts etc.)
 */
public class ThemeManager {

    /**
     * theme
     */
    protected static FlatLaf theme;

    /**
     * sets the theme of the list
     * @param theme theme that should be set
     */
    public static void setTheme(FlatLaf theme) {
        ThemeManager.theme = theme;
    }

    /**
     * gets the defaults of the Ui and returns them
     * @return default values of the Ui
     */
    public static UIDefaults getDefaults() {
        return theme.getDefaults();
    }

    /**
     * gets the default font used on a label
     * @return default font
     */
    public static Font getDefaultFont() {
        return theme.getDefaults().getFont("Label.font");
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
}
