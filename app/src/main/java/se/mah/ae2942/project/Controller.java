package se.mah.ae2942.project;



/**
 * Controller class.
 */
public class Controller {

    private MainActivity mainActivity;
    private ExpenseDB db;

    /**
     * Constructor
     */
    public Controller(MainActivity mainActivity) {
        db = new ExpenseDB(mainActivity);
        this.mainActivity = mainActivity;
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

    public void setData(Expense expense){
        db.insertData(expense);
    }

    public Expense[] getData(){
        return db.getData();
    }

    public void dropDatabase(){
        db.dropTable();
    }
}
