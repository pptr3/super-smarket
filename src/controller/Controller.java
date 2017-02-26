package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import java.util.Map;

import model.Lot;
import model.LotWithActions;

public interface Controller {

    /**
     * If the file indicated by filepath exist, pass an Optional of
     * ObjectInputStream, else pass an Optional.empty.
     * 
     * @throws IOException
     * @param filepath
     */
    void initialize(String filepath) throws FileNotFoundException, IOException;

    /**
     * Saves the file to the given path
     * 
     * @param filepath
     *            ObjectOutputStream
     * @throws IOException
     * @throws FileNotFoundException
     */
    void saveFile(String filepath) throws FileNotFoundException, IOException;

    /**
     * @return the List of Lot
     */

    List<Lot> getList();

    /**
     * @param lotto
     *            adds lotto
     */

    void addLotto(Lot lotto);

    /**
     * Removes n products from the lot with the specified id
     * 
     * @param id
     * @param n
     */
    void removeFromLotto(int ID, int n);

    /**
     * 
     * @param s
     *            prende una stringa, e dipendentemente da questa metter� i
     *            lotti in sconto
     * @return lista di lotti aggiornata con gli sconti
     */
    Map<Lot, Integer> getDiscountable(String s);

    /**
     * Set on sale the lot with specified id of discoutAmount amount
     * 
     * @param id
     * @param discountAmount
     */

    void setOnSale(int id, int discountAmount);

    /**
     * Starts the strategy that allert you if some products need to be discounted
     */
    void startScan();

    /**
     * Stops the strategy that allert you if some products need to be discounted
     */
    void stopScan();

    /**
     * Registers a new view to be controlled (pattern Observer).
     * 
     * @param view
     *            the view to register
     */
    void registerView(MyFakeView view);

}
