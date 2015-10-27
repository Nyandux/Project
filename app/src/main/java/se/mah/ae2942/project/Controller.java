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
    private GMapsFragment gMapsFragment;
    private AddFragment addFragment;

    /**
     * Constructor
     */

    public Controller(MainActivity mainActivity, ListFragment listFragment, UserFragment userFragment, ChartFragment chartFragment,
                      GMapsFragment gMapsFragment, AddFragment addFragment) {

        db = new ExpenseDB(mainActivity);
        this.mainActivity = mainActivity;
        this.listFragment = listFragment;
        this.userFragment = userFragment;
        this.chartFragment = chartFragment;
        this.gMapsFragment = gMapsFragment;
        this.addFragment = addFragment;

        listFragment.setController(this);
        userFragment.setController(this);
        chartFragment.setController(this);
        gMapsFragment.setController(this);
        addFragment.setController(this);

        listFragment.setListViewAdapter();

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
