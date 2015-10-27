package se.mah.ae2942.project;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

/**
 * ListView fragment, displays all the expenses.
 * Worked on: Ragnar Einestam
 */
public class ListFragment extends Fragment {

    private View view;
    private ListView listView;
    private Button btnChart, btnMap;
    private Controller controller;

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
        setListViewAdapter();
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
     * Sets local Controller.
     *
     * @param controller input
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * ButtonListener
     */
    private class ButtonListener implements View.OnClickListener {

        public void onClick(View v) {


            if (btnMap.isPressed()) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                GMapsFragment gMapsFragment = new GMapsFragment();
                ft.addToBackStack(null);
                ft.replace(R.id.activity_main_layout, gMapsFragment).commit();
            }

            if(btnChart.isPressed()){
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ChartFragment chartFragment = new ChartFragment();
                chartFragment.setController(controller);
                ft.addToBackStack(null);
                ft.replace(R.id.activity_main_layout, chartFragment).commit();
            }
        }
    }
}