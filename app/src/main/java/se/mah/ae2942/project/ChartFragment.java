package se.mah.ae2942.project;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Google chart class, summarizes the expenses in a donut diagram.
 */
public class ChartFragment extends Fragment {

    private View view;

    public ChartFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chart, container, false);
        return view;
    }


}
