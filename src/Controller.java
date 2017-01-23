import java.util.List;

public interface Controller {

	/**
	 * carica il file 
	 */
	void initialize(String filepath);
	/**@param ModifyList
	 * ModifyList è un interfaccia con un metodo che mi ritornerà: o la lista dei Lotti ordinati, 
	 * od eliminare dei lotti
	 * @return
	 */
	/**
	 * salva il file
	 * @param filepath
	 */
	void saveFile(String filepath);
	
	List<Lotto> getList();
	/**
	 * aggiungo un lotto
	 * @param lotto
	 */
	void addLotto(Lotto lotto);
	/**
	 * rimuove un elemento dal lotto con id ID
	 * @param ID
	 * @param n numero di elementi da rimuovere dal lotto
	 */ 
	void removeFromLotto(int ID, int n);
	/**
	 * 
	 * @param s prende una stringa, e dipendentemente da questa metterà i lotti in sconto
	 * @return lista di lotti aggiornata con gli sconti
	 */
	List<Pair<Lotto, Integer>> getDiscountable(String s);
	/**
	 * metti in sconto i prodotti con id X e sconto Y
	 * @param list
	 */
	
	void setOnSale(List<Pair<Integer, Integer>> list);
	/**
	 * faccio partire la strategia che mi guarda se determinati prodotti sono da mettere
	 * in sconto
	 */
	void startScan();
	/**
	 * stop strategia
	 */
	void stopScan();
	
	
	
}
