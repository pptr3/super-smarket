package controller;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.List;

import java.util.Map;

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
     * If the file indicated by filepath exist, pass an Optional of
     * ObjectInputStream, else pass an Optional.empty.
     * 
     * @throws IOException
     *             IOException
     * @throws FileNotFoundException
     *             file not found
     * @param filepath
     *            filepath
     */
    void initialize(String filepath) throws FileNotFoundException, IOException;

    /**
     * Saves the file to the given path.
     * 
     * @param filepath filepath
     * @throws IOException IOException
     * @throws FileNotFoundException file not found
     */
    void saveFile(String filepath) throws FileNotFoundException, IOException;

    /**
     * @param mfl modify list
     * @return the List of Lot
     */

    List<Lot> getList(ModifyList mfl);

    /**
     * @param lotto
     *            adds lotto
     */

    void addLotto(Lot lotto);

    /**
     * Removes n products from the lot with the specified id.
     * 
     * @param id id of lot
     * @param n how many products to remove
     */
    void removeFromLotto(int id, int n);

    /**
     * Suggests which lots should be discounted.
     * @param ds strategy for deciding discount
     * @return map of lots that should be discounted, and the amount in percentage
     */
    Map<Lot, Integer> getDiscountable( DiscountStrategy ds);

    /**
     * Set on sale the lot with specified id of discoutAmount amount.
     * 
     * @param id id of the lot
     * @param discountAmount amount
     */

    void setOnSale(int id, int discountAmount);

    /**
     * Starts the strategy that alert you if some products need to be
     * discounted.
     */
    void startScan();

    /**
     * Stops the strategy that alert you if some products need to be discounted.
     */
    void stopScan();

    /**
     * Registers a new view to be controlled (pattern Observer).
     * 
     * @param view
     *            the view to register
     */
    void registerView(View view);


}
