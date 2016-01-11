package se.mah.ae2942.project;



/**
 * Controller class.
 */
public class Controller {

    private ExpenseDB db;

    /**
     * Constructor
     */
    public Controller(MainActivity mainActivity) {
        db = new ExpenseDB(mainActivity);
    }

    /**
     * Returns total amount for all expenses
     * @return - amount to return
     */
    public double getTotalAmount(){
        Expense[] expenses = db.getData();
        double amount = 0;
        for(Expense e : expenses){
            amount += e.getAmount();
        }
        return amount;
    }

    /**
     * Add new expense to DB
     * @param expense Expense
     */
    public void setData(Expense expense){
        db.insertData(expense);
    }

    /**
     * Returns data from database
     * @return Expense[]
     */
    public Expense[] getData(){
        return db.getData();
    }

    /**
     * Drop database table
     */
    public void dropDatabase(){
        db.dropTable();
    }
}
