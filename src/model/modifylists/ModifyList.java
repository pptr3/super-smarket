package model.modifylists;
import java.util.List;

import model.Lot;

/**
 * One-method interface that modifies the list of lots by either sorting it or removing some elements.
 */
public interface ModifyList {

    /**
     * filters and/or sorts the given list of lots.
     * @param lot the initial list
     * @return the modified list
     */
    List<Lot> modify(List<Lot> lot);

}
