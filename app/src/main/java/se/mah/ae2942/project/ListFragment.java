package se.mah.ae2942.project;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

/**
 * ListView fragment, displays all the expenses.
 * Worked on: Ragnar Einestam
 */
public class ListFragment extends Fragment {

    private View view;
    private ListView listView;
    private Button btnChart, btnMap;
    private Controller controller;
    private MainActivity mainActivity;

    /**
     * Constructor
     */
    public ListFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        initiate();

        return view;
    }

    /**
     * Initiates variables.
     */
    public void initiate() {
        listView = (ListView) view.findViewById(R.id.fragment_list_listview);
        btnChart = (Button) view.findViewById(R.id.fragment_list_button_chart);
        btnMap = (Button) view.findViewById(R.id.fragment_list_button_map);
        btnMap.setOnClickListener(new ButtonListener());
        btnChart.setOnClickListener(new ButtonListener());
        mainActivity = (MainActivity)getActivity();
        controller = mainActivity.getController();
    }

    @Override
    public void onResume() {
        setListViewAdapter();
        super.onResume();
    }

    /**
     * Set Listview Adapter
     */
    public void setListViewAdapter() {
        ExpenseAdapter expenseAdapter = new ExpenseAdapter(getActivity(), controller.getData());
        listView.setAdapter(expenseAdapter);
    }


    /**
     * ButtonListener
     */
    private class ButtonListener implements View.OnClickListener {

        public void onClick(View v) {


            if (btnMap.isPressed()) {
                mainActivity.setViewFragment("gmapsfragment");
            }

            if(btnChart.isPressed()){
                mainActivity.setViewFragment("chartfragment");
            }
        }
    }
}