package model;

import java.util.List;
import java.util.Map;

public interface DiscountStrategy {

    Map<Lot, Integer> suggestDiscounts(List<Lot> lots);
    
}
