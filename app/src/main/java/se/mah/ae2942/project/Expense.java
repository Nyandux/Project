package se.mah.ae2942.project;

/**
 * Expense class, contains the different values of a new expense.
 * Worked on: Ragnar Einestam, Arbi Arestakesians
 */
public class Expense {

    private String title;
    private String category;
    private String amount;
    private String date;
    private double longitude;
    private double latitude;
    //test

    /**
     * Constructor, initiates the local variables to parameter values.
     * @param category - category of transaction
     * @param title - title of transaction
     * @param amount - amount of transaction
     * @param date - date of transaction
     * @param longitude - longitude coordinate of transaction
     * @param latitude - latitude coordinate of transaction
     */
    public Expense(String title, String category, String amount, String date, double longitude, double latitude) {
        this.category = category;
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Returns the category.
     * @return category
     */
    public String getCategory(){
        return category;
    }

    /**
     * Returns the title.
     * @return title
     */
    public String getTitle(){
        return title;
    }

    /**
     * Returns the amount.
     * @return amount
     */
    public String getAmount(){
        return amount;
    }

    /**
     * Returns the date.
     * @return date
     */
    public String getDate(){
        return date;
    }

    /**
     * Returns the longitude coordinate for transaction;
     * @return longitude coordinate
     */
    public double getLongitude(){
        return longitude;
    }

    /**
     * Returns the latitude coordinate for transaction;
     * @return longitude coordinate
     */
    public double getLatitude(){
        return latitude;
    }
}
