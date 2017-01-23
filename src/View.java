import java.util.List;

public interface View {
	
	/*
	 * called to advice possible discounts
	 * @param lotti
	 */
	void discountAdvice(List<Pair<Lotto, Integer>> lotti);

	
}
