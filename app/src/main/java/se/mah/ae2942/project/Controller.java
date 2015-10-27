package se.mah.ae2942.project;

import android.database.Cursor;

import com.google.android.gms.maps.MapFragment;
//

/**
 * Controller class.
 */
public class Controller {

    private MainActivity mainActivity;
    private ExpenseDB db;
    private ListFragment listFragment;
    private UserFragment userFragment;
    private ChartFragment chartFragment;
    private GMapsFragment gMapFragment;

    /**
     * Constructor
     */

    public Controller(MainActivity mainActivity, ListFragment listFragment, UserFragment userFragment, ChartFragment chartFragment, GMapsFragment gMapsFragment) {

        db = new ExpenseDB(mainActivity);

        this.mainActivity = mainActivity;
        this.listFragment = listFragment;
        this.userFragment = userFragment;
        this.chartFragment = chartFragment;
        this.gMapFragment = gMapsFragment;
    }

    //add code
    public void setData(Expense expense){
        db.insertData(expense);
    }

    public Expense[] getData(){
        return db.getData();
    }

    public ExpenseAdapter createListAdapter(){
        return new ExpenseAdapter(mainActivity, db.getData());
    }

    public void dropDatabase(){
        db.dropTable();
    }
}
