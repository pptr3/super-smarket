package controller;

import java.util.List;

import model.Lotto;
import model.Pair;

public interface MyFakeView {

	/**
	 * called to advice possible discounts
	 * @param lotti
	 */
	void discountAdvice(List<Pair<Lotto, Integer>> lotti);
}
