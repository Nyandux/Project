package se.mah.ae2942.project;

import android.database.Cursor;

import com.google.android.gms.maps.MapFragment;

/**
 * Controller class.
 */
public class Controller {

    private MainActivity main;
    private AddFragment addFragment;
    private ChartFragment chartFragment;
    private Expense expense;
    private MapFragment mapFragment;
    private UserFragment userFragment;
    private ExpenseDB db = new ExpenseDB(main);

    /**
     * Constructor
     */
    public Controller(MainActivity main, AddFragment addFrag, ChartFragment chartFrag,
                    Expense expense, UserFragment userFrag){
        this.main = main;
        this.addFragment = addFragment;
        this.chartFragment = chartFrag;
        this.expense = expense;
        this.userFragment = userFrag;
    }

    //add code
    public Expense[] getExpense(){
         return null;
    }

    //add code
    public void setData(Expense expense){
        db.insertData(
                expense.getTitle(),
                expense.getCategory(),
                expense.getAmount(),
                expense.getDate()
                //expense.getLongitude(),
                //expense.getLatitude(),
        );
    }

    public void getData(){
        Cursor result = db.getData();
    }




    /**
     * Sets local MainActivity.
     * @param main input
     */
    public void setMain(MainActivity main){
        this.main = main;
    }
}
