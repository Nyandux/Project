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

import com.google.android.gms.maps.GoogleMap;

/**
 * MainActivity class..
 */
public class MainActivity extends AppCompatActivity {
    Controller controller;
    private UserFragment userFragment;
    private GMapsFragment gMapsFragment;
    private ChartFragment chartFragment;
    private ListFragment listFragment;
    private AddFragment addFragment;

    private SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.app_icon_small);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("MainActivity",
                Activity.MODE_PRIVATE);

        userFragment = new UserFragment();
        gMapsFragment = new GMapsFragment();
        chartFragment = new ChartFragment();
        listFragment = new ListFragment();
        addFragment = new AddFragment();


        controller = new Controller(this,listFragment,userFragment,chartFragment,gMapsFragment, addFragment);

        //If there is a username from previously, go to ListFragment, else prompt new username.
        if (savedInstanceState == null) {
            if (sharedPreferences.contains("username")) {
                controller.setViewListFragment();
            } else {
                controller.setViewUserFragment();
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
            controller.setViewUserFragment();
        }

        if (id == R.id.action_empty_database) {
            controller.dropDatabase();
            Toast.makeText(this, "Table dropped", Toast.LENGTH_LONG).show();
        }

        if(id == R.id.action_add_to_list){
            controller.setViewAddFragment();
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
