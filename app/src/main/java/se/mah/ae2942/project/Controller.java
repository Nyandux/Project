package se.mah.ae2942.project;

import android.database.Cursor;

import com.google.android.gms.maps.MapFragment;
//

/**
 * Controller class.
 */
public class Controller {

    private MainActivity main;
    private ListFragment listFragment;
    private UserFragment userFragment;
    private ChartFragment chartFragment;
    private GMapsFragment gMapsFragment;
    private ExpenseDB db;

    /**
     * Constructor
     */
    public Controller(MainActivity main, ListFragment listFragment, UserFragment userFragment, ChartFragment chartFragment,
                      GMapsFragment gMapsFragment){
        db = new ExpenseDB(main);
        this.main = main;
        this.listFragment = listFragment;
        this.userFragment = userFragment;
        this.chartFragment = chartFragment;
        this.gMapsFragment = gMapsFragment;
    }

    //add code
    public void setData(Expense expense){
        db.insertData(expense);
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
