package model;
import java.util.List;
import java.util.Map;

public interface Model {

	/**
	 * 
	 * @param serializedModel content of the file where the state is saved
	 */
	void initialize(String serializedModel);
	
	/**
	 * 
	 * @return the serialized version of the Model, to save in a file
	 */
	String serializeModel();

	/**
	 * 
	 * @param mfl interface with only one method for either sorting the list or removing objects from it
	 * 
	 * @return
	 */
	List<Lot> getList(ModifyList mfl);
	
	void addLotto(Lot lot);

	/**
	 * Removes n elements from the lot. If the lot has 0 items it gets removed 
	 * @param ID
	 * @param n
	 */
	void removeFromLot(int ID, int n);
	
	/**
	 * 
	 * @param ds strategy for deciding discount
	 * @return
	 */
	Map<Lot, Integer> getDiscountable(DiscountStrategy ds);
	
	void setOnSale(Map<Integer, Integer> list);
	
}
