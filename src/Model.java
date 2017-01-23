import java.util.List;

public interface Model {

	/**
	 * 
	 * @param serializedModel content of the Filepath where the state is saved
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
	List<Lotto> getList(ModifyList mfl);
	
	void addLotto(Lotto lotto);

	/**
	 * Removes n elements from the lotto. If the lotto has 0 items it gets removed 
	 * @param ID
	 * @param n
	 */
	void removeFromLotto(int ID, int n);
	
	/**
	 * 
	 * @param s strategy for deciding discount
	 * @return
	 */
	List<Pair<Lotto, Integer>> getDiscountable(String s);
	
	void setOnSale(List<Pair<Integer, Integer>> list);
	
}
