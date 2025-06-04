package de.dhsn_ooe.todo.UI.Helpers;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.UIDefaults;

import com.formdev.flatlaf.FlatLaf;

public class FontManager {
    protected static FlatLaf theme;

    public static void setTheme(FlatLaf theme) {
        FontManager.theme = theme;
    }

    public static UIDefaults getDefaults() {
        return theme.getDefaults();
    }
    public static Font getDefaultFont() {
        return theme.getDefaults().getFont("Label.font");
    }

    public static Font modifyDefaultFont(Map<TextAttribute, Object> newAttributes) {
        Map<TextAttribute, Object> attributes = new HashMap<>(getDefaultFont().getAttributes());
        attributes.putAll(newAttributes);
        return getDefaultFont().deriveFont(attributes);
    }
}
