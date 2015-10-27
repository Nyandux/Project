package se.mah.ae2942.project;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
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
    private Location location;
    private LocationManager locationManager;
    private Double latitude,longtitude;

    /**
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
    }

    /**
     * Sets local controller.
     * @param controller input
     */
    public void setController(Controller controller){
        this.controller = controller;
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
    public Double getAmount(){
        return Double.parseDouble(etAmount.getText().toString());
    }

    public String getCategory(){
        return categorySelected.toString();
    }

    public String getDate(){
        return date.toString();
    }

    public Double getLatitude(){
        locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        location = locationManager.getLastKnownLocation(provider);
        latitude = location.getLatitude();

        return latitude;
    }

    public Double getLongtitude(){
        locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        location = locationManager.getLastKnownLocation(provider);
        longtitude = location.getLongitude();

        return longtitude;
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
        @Override
        public void onClick(View v) {
            Expense expense = new Expense(getTitle(),getCategory(),getAmount(),getDate(),getLongtitude(),getLatitude());
            controller.setData(expense);
        }
    }
}