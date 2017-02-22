package model;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import model.discountstrategies.DiscountStrategy;
import model.modifylists.ModifyList;

/**
 * The model of the MVC pattern for supersmarket.
 * Represents a warehouse that has various operations on lots.
 */
public interface Model {

        /**
         * Initializes the content of the warehouse.
         * @param serializedModel ObjectInputStream
         */
        void initialize(Optional<ObjectInputStream> serializedModel);


        /**
         * Writes the serialized version of the warehouse in the output stream.
         * @param output ObjectOutputStream decorating some file stream given by the controller
         */
        void serializeModel(ObjectOutputStream output);

        /**
         * Returns the content of the warehouse after modifying its content.
         * 
         * @param mfl interface with only one method for either sorting the list or removing objects from it
         * 
         * @return the list of lots after mfl acted upon it
         */
        List<Lot> getList(ModifyList mfl);

        /**
         * Adds the Lot to the warehouse.
         * @param lot new Lot to be added
         */
        void addLotto(Lot lot);

        /**
         * Removes n elements from the lot. If the lot has 0 items it gets removed. 
         * @param id id of the lot
         * @param n number of elements to remove from the lot
         */
        void removeFromLot(int id, int n);

        /**
         * Suggests which lots should be discounted.
         * @param ds strategy for deciding discount
         * @return map of lots that should be discounted, and the amount in percentage
         */
        Map<Lot, Integer> getDiscountable(DiscountStrategy ds);

        /**
         * Sets the lot with the given id to discount at the given amount.
         * @param id the id of the lot to be discounted
         * @param discountAmount the discount amount in percentage
         */
        void setOnSale(int id, int discountAmount);



}
