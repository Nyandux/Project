package se.mah.ae2942.project;

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

    /**
     * Sets local MainActivity.
     * @param main input
     */
    public void setMain(MainActivity main){
        this.main = main;
    }
}
