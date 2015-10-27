package se.mah.ae2942.project;

import android.database.Cursor;

import com.google.android.gms.maps.MapFragment;

/**
 * Controller class.
 */
public class Controller {

    private MainActivity main;
    private ExpenseDB db;

    /**
     * Constructor
     */
    public Controller(MainActivity main){
        db = new ExpenseDB(main);
        this.main = main;
    }

    //add code
    public void setData(Expense expense){
       // db.insertData(expense);
    }

    public Expense[] getData(){
        return db.getData();
    }

    public ExpenseAdapter createListAdapter(){
        return new ExpenseAdapter(main, db.getData());
    }

    public void dropDatabase(){
        db.dropTable();
    }
}
