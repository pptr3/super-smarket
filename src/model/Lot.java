package model;

import java.util.Date;

public interface Lot {

        int getId(); 
    
	String getName();
	
	Date getExpirationDate();
	
	int getCurrentQuantity();
	
	int getInitialQuantity();
	
	Date getCheckInDate();
	
	/**
	 * Price is in cents of euro
	 * @return cents of euro
	 */
	int getPricePerSingleItem();
	
	boolean isOnSale();
	
	void removeElements(int n);
	
}
