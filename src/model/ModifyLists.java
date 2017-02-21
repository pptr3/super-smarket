package model;

/**
 * Enum that should be used as the only access point for the various ModifyList possibilities from outside of the model.
 */
public enum ModifyLists {

        ALPHABETICAL_SORTING(new AlphabeticalSorting()),
        ONLY_EXPIRING(new OnlyExpiring());

        private ModifyList mfl;

        ModifyLists (ModifyList mfl) {
            this.mfl = mfl;
        }

        ModifyList getMfl() {
            return this.mfl;
        }

}

