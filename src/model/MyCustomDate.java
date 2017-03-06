package model;

/** 
 * Provides really basic date methods.
 */
public interface MyCustomDate {

    /**
     * Computes the difference in days between this and another MyCustomDate.
     * @param other the other date we want to compare this one to 
     * @return the number of days separating this date from the other. Negative if other is after this
     */
    int getDifferenceInDays(MyCustomDate other);

    /**
     * Returns the year of the date.
     * @return returns the year of the date
     */
    int getYear();

    /**
     * Returns which day of the year it is (1-366).
     * @return Returns which day of the year it is (1-366)
     */
    int getDayOfYear();

    /**
     * Visual representation of the date.
     * @return date in the format yyyy-mm-dd
     */
    String getDateToString();

}
