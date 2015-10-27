package se.mah.ae2942.project;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.gms.maps.GoogleMap;

/**
 * MainActivity class..
 */
public class MainActivity extends AppCompatActivity {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.dollar_logo1);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("MainActivity",
                Activity.MODE_PRIVATE);

        Controller controller = new Controller(this);

        //If there is a username from previously, go to ListFragment, else prompt new username.
        if (savedInstanceState == null) {
            if (sharedPreferences.contains("username")) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ListFragment listFragment = new ListFragment();
                listFragment.setController(controller);
                ft.addToBackStack(null);
                ft.replace(R.id.activity_main_layout, listFragment).commit();
            } else {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                UserFragment userFragment = new UserFragment();
                ft.addToBackStack(null);
                ft.replace(R.id.activity_main_layout, userFragment).commit();
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();



        if (id == R.id.action_log_out) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            UserFragment userFragment = new UserFragment();
            ft.addToBackStack(null);
            ft.replace(R.id.activity_main_layout, userFragment).commit();
        }

        if (id == R.id.action_empty_database) {

        }

        if(id == R.id.action_add_to_list){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            AddFragment addFragment = new AddFragment();
            ft.addToBackStack(null);
            ft.replace(R.id.activity_main_layout, addFragment).commit();
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
