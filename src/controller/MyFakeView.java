package controller;

import java.util.Map;

import model.Lot;


public interface MyFakeView {

	/**
	 * called to advice possible discounts
	 * @param lotti
	 */
	void discountAdvice(Map<Lot, Integer> lotti);
}
