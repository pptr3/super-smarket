package model;

/**
 * Basic implementation of ModifyListFactory, that has all the ModifyLists of the model.
 */
public class ModifyListFactoryImpl implements ModifyListFactory {

    @Override
    public ModifyList alphabeticalSorting() {
        return new AlphabeticalSorting();
    }

    @Override
    public ModifyList onlyExpiring() {
        return new OnlyExpiring();
    }

}
