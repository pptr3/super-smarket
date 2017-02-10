package model;

/** 
 * Provides really basic date methods.
 */
public interface MyCustomDate {

    /**
     * Creates a MyCustomDate with the given parameters.
     * @param year 0 - 9999
     * @param month 1-12
     * @param day 1-31
     * @return a new instance of MyCustomDate
     */
    MyCustomDate create(int year, int month, int day);

    /**
     * Computes the difference in days between this and another MyCustomDate.
     * @param other the other date we want to compare this one to 
     * @return the number of days separating this date from the other. Negative if other is after this
     */
    int getDifferenceInDays(MyCustomDate other);

}
