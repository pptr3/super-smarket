package model.resourcebundle;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 * 
 *
 */
public class ResourceBound implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3325080430848316075L;
    private final transient ResourceBundle res;
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