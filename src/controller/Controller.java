package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import model.Lot;
import model.discountstrategies.DiscountStrategy;
import model.modifylists.ModifyList;
import view.View;

/**
 * A Controller interface.
 *
 */
public interface Controller {

    /**
     * If the file indicated by file path exist, pass an Optional of
     * ObjectInputStream, else pass an Optional.empty.
     * 
     * @throws IOException
     *             IOException
     * @throws FileNotFoundException
     *             file not found
     * @param filepath file path
     */
    void initialize(String filepath) throws FileNotFoundException, IOException;

    /**
     * Saves the file to the given path.
     * 
     * @param filepath
     *            file path
     * @throws IOException
     *             IOException
     * @throws FileNotFoundException
     *             file not found
     */
    void saveFile(String filepath) throws FileNotFoundException, IOException;

    /**
     * @param mfl
     *            modify list
     * @return the list of Lot
     */

    List<Lot> getList(ModifyList mfl);

    /**
     * @param lot
     *            the lot to add
     */

    void addLot(Lot lot);

    /**
     * Removes n products from the lot with the specified id.
     * 
     * @param id
     *            id of lot
     * @param n
     *            how many products to remove
     */
    void removeFromLot(int id, int n);

    /**
     * Suggests which lots should be discounted.
     * 
     * @param ds
     *            strategy for deciding discount
     * @return map of lots that should be discounted, and the amount in
     *         percentage
     */
    Map<Lot, Integer> getDiscountable(DiscountStrategy ds);

    /**
     * Set on sale the lot with specified id of discoutAmount amount.
     * 
     * @param id
     *            id of lot
     * @param discountAmount
     *            amount
     */

    void setOnSale(int id, int discountAmount);

    /**
     * Remove from discount the lot with specified id.
     * @param id
     *            the id of the lot to be removed from sale
     */
    void removeFromSale(int id);

    /**
     * Starts the strategy that alerts if some lots need to be
     * discounted.
     */
    void startScan();

    /**
     * Stops the strategy that alerts if some lots need to be discounted.
     */
    void stopScan();

    /**
     * Registers a view to be controlled (pattern Observer).
     * 
     * @param view
     *            the view to register
     */
    void registerView(View view);

    /**
     * Says that the given lot should not be suggested as a discount in this
     * session anymore.
     * 
     * @param l
     *            the lot not to put in discount
     */
    void dontSuggestAnymore(Lot l);

    /**
     * Empties the list of lots that shouldn't be suggested as discount in this
     * session.
     */
    void resetSuggestions();

    /**Getter that return the subject.
     * @return the Subject
     */
    Subject getSubject();
}
