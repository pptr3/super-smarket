package model;

import java.util.Date;
import java.util.Optional;

public interface Lot {

        int getId(); 
    
	String getName();
	
	Optional<MyCustomDate> getExpirationDate();
	
	MyCustomDate getCheckInDate();

	int getInitialQuantity();

	int getCurrentQuantity();

	/**
	 * Price is in cents of euro
	 * @return cents of euro
	 */
	int getPricePerSingleItem();
	
	boolean isOnSale();
	
	int getSalePercentage(); 
}
