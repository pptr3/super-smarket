package model;

import java.util.Date;

public interface Lot {

	String getName();
	
	Date getExpirationDate();
	
	int getQuantity();
	
	Date getCheckInDate();
	
	/**
	 * Price is in cents of euro
	 * @return cents of euro
	 */
	int getPricePerSingleItem();
	
	boolean isOnSale();
	
}
