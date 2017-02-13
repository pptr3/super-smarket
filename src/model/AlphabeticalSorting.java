package model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Sorts the lots in alphabetical order.
 */
public class AlphabeticalSorting implements ModifyList {

    @Override
    public List<Lot> modify(final List<Lot> lot) {
        return lot.stream().sorted((l, o) -> l.getName().compareTo(o.getName()))
                .collect(Collectors.toList());
        }

}
