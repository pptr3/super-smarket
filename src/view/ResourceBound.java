package view;

import java.util.Locale;
import java.util.ResourceBundle;
/**
 * 
 *
 */
public class ResourceBound {

    private final ResourceBundle res;
    /**
     * 
     */
    public ResourceBound() {
        Locale.setDefault(new Locale("en", "US"));
        this.res = ResourceBundle.getBundle("Lingua", Locale.getDefault());
    }
/**
 * 
 * @param text text
 * @return the text message
 */
    public String setName(final String text) {
        return this.res.getString(text);
    }
}