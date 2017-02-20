package view;
import java.util.List;
import java.util.Map;

import model.Lot;


public interface View {
	
	/**
	 * called to advice possible discounts
	 * @param lotti
	 */
	void discountAdvice(Map<Lot, Integer> lotti);

	
}
