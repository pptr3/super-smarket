package controller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import java.util.Map;

import model.Lot;
import model.LotWithActions;

public interface Controller {
	/**
	 * carica il file 
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	void initialize(String filepath) throws FileNotFoundException, IOException;
	/**@param ModifyList
	 * ModifyList � un interfaccia con un metodo che mi ritorner�: o la lista dei Lotti ordinati, 
	 * od eliminare dei lotti
	 * @return
	 */
	/**
	 * salva il file
	 * @param filepath
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	void saveFile(String filepath) throws FileNotFoundException, IOException;
	
	/**
	 * @return a modify list of Lotto
	 */
	
	List<Lot> getList();
	/**
	 * aggiungo un lotto
	 * @param lotto
	 * @return 
	 */
	void addLotto(Lot lotto);
	/**
	 * rimuove n elementi dal lotto con identificatore ID
	 * @param ID
	 * @param n numero di elementi da rimuovere dal lotto
	 */ 
	void removeFromLotto(int ID, int n);
	/**
	 * 
	 * @param s prende una stringa, e dipendentemente da questa metter� i lotti in sconto
	 * @return lista di lotti aggiornata con gli sconti
	 */
	Map<Lot, Integer> getDiscountable(String s);
	/**
	 * metti in sconto i prodotti con id X e sconto Y
	 * @param list
	 */
	
	void setOnSale(int id, int discountAmount);
	
    /**
     * faccio partire la strategia che mi guarda se determinati prodotti sono da
     * mettere in sconto
     */
    void startScan();

    /**
     * stop strategia
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
