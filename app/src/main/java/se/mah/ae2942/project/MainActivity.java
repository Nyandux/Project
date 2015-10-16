package se.mah.ae2942.project;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Map;

/**
 * MainActivity class.
 */
public class MainActivity extends AppCompatActivity {
    private Button googleMapButton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        AddFragment lm = new AddFragment();
        ft.replace(R.id.activity_main_layout, lm).commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_log_out) {
            return true;
        }

        if (id == R.id.action_empty_database) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class GoogleMapButtonListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        }
    }
}
