package model;

/**
 * Factory that instantiates ModifyList objects.
 */
public interface ModifyListFactory {

    /**
     * Creates a ModifyList that sorts items alphabetically.
     * @return an AlphabeticalSorting object
     */
    ModifyList alphabeticalSorting();

    /**
     * Creates a ModifyLists that removes items which do not have an expiring date.
     * @return an OnlyExpiring object
     */
    ModifyList onlyExpiring();

}
