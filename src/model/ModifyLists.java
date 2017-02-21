package model;

/**
 * Enum that should be used as the only access point for the various ModifyList possibilities from outside of the model.
 */
public enum ModifyLists {

        /**
         * Alphabetical sorting.
         */
        ALPHABETICAL_SORTING(new AlphabeticalSorting()),
        /**
         * Removes all items that don't have an expiration date.
         */
        ONLY_EXPIRING(new OnlyExpiring());

        private ModifyList mfl;

        ModifyLists (ModifyList mfl) {
            this.mfl = mfl;
        }

        /**
         * getter for the modifyList.
         * @return mfl
         */
        public ModifyList getMfl() {
            return this.mfl;
        }

}

