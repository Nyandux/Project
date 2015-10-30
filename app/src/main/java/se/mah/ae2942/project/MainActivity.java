package se.mah.ae2942.project;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * MainActivity class..
 */
public class MainActivity extends AppCompatActivity {

    private Controller controller;
    private ListFragment listFragment;
    private UserFragment userFragment;
    private GMapsFragment gMapsFragment;
    private ChartFragment chartFragment;
    private AddFragment addFragment;

    private SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_medium);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("MainActivity",
                Activity.MODE_PRIVATE);

        controller = new Controller(this);
        listFragment = new ListFragment();
        userFragment = new UserFragment();
        addFragment = new AddFragment();
        gMapsFragment = new GMapsFragment();
        chartFragment = new ChartFragment();

        //If there is a username from previously, go to ListFragment, else prompt new username.
        if (savedInstanceState == null) {
            if (sharedPreferences.contains("username")) {
                setViewFragment("listfragment");
            } else {
                setViewFragment("userfragment");
            }
        }
    }

    public void setViewFragment(String str){

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack(null);

        switch (str){
            case "listfragment" :
                ft.replace(R.id.activity_main_layout, listFragment).commit();
                break;
            case "userfragment" :
                ft.replace(R.id.activity_main_layout, userFragment).commit();
                break;
            case "gmapsfragment" :
                ft.replace(R.id.activity_main_layout, gMapsFragment).commit();
                break;
            case "chartfragment" :
                ft.replace(R.id.activity_main_layout, chartFragment).commit();
                break;
            case "addfragment" :
                ft.replace(R.id.activity_main_layout, addFragment).commit();
                break;
        }
    }

    public Controller getController() {
        return controller;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onDestroy(){
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_log_out) {
            setViewFragment("userfragment");
        }

        if (id == R.id.action_empty_database) {
            controller.dropDatabase();
            Toast.makeText(this, "Table dropped", Toast.LENGTH_LONG).show();
        }

        if(id == R.id.action_add_to_list){
            setViewFragment("addfragment");
        }

        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
