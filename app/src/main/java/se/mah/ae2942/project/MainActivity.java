package se.mah.ae2942.project;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.icon_new);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ListFragment listFragment = new ListFragment();
        ft.addToBackStack(null);
        ft.replace(R.id.activity_main_layout, listFragment).commit();
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
}
