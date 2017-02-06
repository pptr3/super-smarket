package model;

import java.util.Date;
import java.util.Optional;

public interface Lot {

        int getId(); 
    
	String getName();
	
	Optional<Date> getExpirationDate();
	
	Date getCheckInDate();

	int getInitialQuantity();

	int getCurrentQuantity();

	/**
	 * Price is in cents of euro
	 * @return cents of euro
	 */
	int getPricePerSingleItem();
	
	boolean isOnSale();
	
	void removeElements(int n);
	
}
