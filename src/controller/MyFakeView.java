package controller;

import java.util.Map;

import model.Lot;

/**
 * FakeView to test my Controller.
 *
 */
public interface MyFakeView {

    /**
     * called to advice possible discounts.
     * 
     * @param lotti map of lots
     */
    void discountAdvice(Map<Lot, Integer> lotti);

    /**
     * update the view.
     */
    void update();
}
