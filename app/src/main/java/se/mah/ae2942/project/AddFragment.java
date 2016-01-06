package se.mah.ae2942.project;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Add fragment, creates a new expense.
 * Worked on: Ragnar Einestam
 */
public class AddFragment extends Fragment {

    private View view;
    private EditText etTitle, etAmount;
    private Button btnCategory, btnDate, btnFinish;
    private Controller controller;
    private String date, categorySelected;
    private String[] categories = {"Entertainment", "Home", "Travel", "Food", "Other"};
    private Calendar cal;
    private MainActivity main;
    private double lng, lat;
    private LocationManager locationManager;
    private boolean isGPSEnabled, isNetworkEnabled;
    private MyLocationListener mll;

    /*;
     * Contstructor.
     */
    public AddFragment() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add, container, false);
        initiate();

        return view;
    }

    /**
     * Initiates variables.
     */
    public void initiate(){
        etTitle = (EditText)view.findViewById(R.id.fragment_add_edittext_title);
        etAmount = (EditText)view.findViewById(R.id.fragment_add_edittext_amount);
        btnCategory = (Button)view.findViewById(R.id.fragment_add_button_category);
        btnDate = (Button)view.findViewById(R.id.fragment_add_button_date);
        btnFinish = (Button)view.findViewById(R.id.fragment_add_button_finish);
        btnCategory.setOnClickListener(new ButtonCategoryOnClick());
        btnDate.setOnClickListener(new ButtonDateOnClick());
        btnFinish.setOnClickListener(new ButtonFinishOnClick());
        cal = Calendar.getInstance();
        main = (MainActivity)getActivity();
        controller = main.getController();
        locationManager = (LocationManager) main.getSystemService(Context.LOCATION_SERVICE);
        // getting GPS status
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // getting network status
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        mll = new MyLocationListener();
    }

    /**
     * Returns the title input from EditText.
     * @return String title.
     */
    public String getTitle(){
        return etTitle.getText().toString();
    }

    /**
     * Returns the amount input from EditText.
     * @return String amount.
     */
    public double getAmount() {
        return Double.parseDouble(etAmount.getText().toString());
    }

    public String getCategory(){
        return categorySelected;
    }

    public String getDate(){
        return date;
    }

    public double getLongitude(){
        return lng;
    }

    public double getLatitude(){
        return lat;
    }

    /**
     * The category button, creates a RadioButton AlertDialog.
     * The user can then pick a category relevant to the transaction.
     */
    private class ButtonCategoryOnClick implements View.OnClickListener {

        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Expense category");
            builder.setSingleChoiceItems(categories, -1, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int i) {
                    categorySelected = categories[i];
                    Toast.makeText(view.getContext().getApplicationContext(),
                            "The selected category is " + categories[i], Toast.LENGTH_LONG).show();

                }
            });
            builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int i) {
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
    }

    /**
     * The Date button, creates a DatePickerDialog that lets you pick a date relevant to the transaction.
     */
    private class ButtonDateOnClick implements View.OnClickListener {

        public void onClick(View v) {
            DatePickerDialog dpd = new DatePickerDialog(view.getContext(), dpListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            dpd.show();
        }
    }

    /**
     * DatePickerDialog listener
     */
    private DatePickerDialog.OnDateSetListener dpListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int yearToPick, int monthOfYear, int dayOfMonth) {

                    date = String.valueOf(yearToPick) + String.format("%02d", monthOfYear+1) + String.format("%02d", dayOfMonth);

                    Toast.makeText(view.getContext().getApplicationContext(),
                            "The selected Date is " + date, Toast.LENGTH_LONG).show();
                }
            };

    private class ButtonFinishOnClick implements View.OnClickListener {

        public void onClick(View v) {
            getLocation();
            Expense expense = new Expense(getTitle(),getCategory(),getAmount(),getDate(), getLatitude(), getLongitude());
            controller.setData(expense);
            main.setViewFragment("listfragment");
        }
    }

    public void getLocation(){
        if (!isGPSEnabled && !isNetworkEnabled) {
            // no GPS Provider and no network provider is enabled
        }
        else
        {   // Either GPS provider or network provider is enabled

            // First get location from Network Provider
            if (isNetworkEnabled)
            {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, mll);
                if (locationManager != null)
                {
                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location != null)
                    {
                        lat = location.getLatitude();
                        lng = location.getLongitude();
                    }
                }
            }// End of IF network enabled

            // if GPS Enabled get lat/long using GPS Services
            if (isGPSEnabled)
            {
                locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 10000,
                        0, mll);
                if (locationManager != null)
                {
                    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location != null)
                    {
                        lat = location.getLatitude();
                        lng = location.getLongitude();
                    }
                }

            }// End of if GPS Enabled
        }// End of Either GPS provider or network provider is enabled
    }

    private class MyLocationListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            Log.d("hej", "" + lat + lng);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}