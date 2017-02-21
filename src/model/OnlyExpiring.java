package model;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Removes all non-expiring elements from the list.
 */
class OnlyExpiring implements ModifyList {

    @Override
    public List<Lot> modify(final List<Lot> lot) {
        return lot.stream().filter(l -> l.getExpirationDate().isPresent())
                .collect(Collectors.toList());
    }

}
