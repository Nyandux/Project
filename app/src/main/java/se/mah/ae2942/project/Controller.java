package se.mah.ae2942.project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.widget.Toast;

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
    private AddFragment addFragment;

    /**
     * Constructor
     */
    public Controller(MainActivity mainActivity, ListFragment listFragment, UserFragment userFragment,
                      ChartFragment chartFragment, GMapsFragment gMapsFragment, AddFragment addFragment) {

        db = new ExpenseDB(mainActivity);
        this.mainActivity = mainActivity;
        this.listFragment = listFragment;
        this.userFragment = userFragment;
        this.chartFragment = chartFragment;
        this.gMapFragment = gMapsFragment;
        this.addFragment = addFragment;

        listFragment.setController(this);
        userFragment.setController(this);
        chartFragment.setController(this);
        gMapsFragment.setController(this);
        addFragment.setController(this);
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

    public void setViewListFragment(){
        FragmentManager fm = mainActivity.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.activity_main_layout, listFragment).commit();
    }


    public void setViewGmapsFragment(){
        FragmentManager fm = mainActivity.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.activity_main_layout, gMapFragment).commit();
    }

    public void setViewChartFragment(){
        FragmentManager fm = mainActivity.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.activity_main_layout, chartFragment).commit();

    }

    public void setView(Fragment fragment){
        if(fragment != null) {
            FragmentManager fm = mainActivity.getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.activity_main_layout, fragment).commit();
        }
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
