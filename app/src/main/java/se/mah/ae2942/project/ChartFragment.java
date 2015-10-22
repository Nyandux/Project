package se.mah.ae2942.project;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.PieChart;

/**
 * Google Chart fragment, summarizes the expenses in a donut diagram.
 * Worked on: Ragnar Einestam
 */
public class ChartFragment extends Fragment {

    private View view;
    private Controller controller;
    private PieChart pieChart;

    /**
     * Constructor
     */
    public ChartFragment() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chart, container, false);
        return view;
    }

    public void initiate() {
        pieChart.setCenterText("Expenses");
    }

    /**
     * Sets local controller.
     * @param controller input
     */
    public void setController(Controller controller){
        this.controller = controller;
    }
}
