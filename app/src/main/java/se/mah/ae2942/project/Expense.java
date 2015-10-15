package se.mah.ae2942.project;

/**
 * Expense class, contains the different values of a new expense.
 */
public class Expense {

    private String title;
    private String category;
    private String amount;
    private String date;
    private String coords;

    /**
     * Constructor, initiates the local variables to parameter values.
     * @param category - category of transaction
     * @param title - title of transaction
     * @param amount - amount of transaction
     * @param date - date of transaction
     */
    public Expense(String title, String category, String amount, String date, String coords) {
        this.category = category;
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.coords = coords;
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
     * Returns the coords;
     * @return coords
     */
    public String getCoords(){
        return coords;
    }
}
